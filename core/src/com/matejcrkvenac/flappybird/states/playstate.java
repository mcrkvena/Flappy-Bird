package com.matejcrkvenac.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.matejcrkvenac.flappybird.FlappyBird;
import com.matejcrkvenac.flappybird.sprites.bird;
import com.matejcrkvenac.flappybird.sprites.wall;

public class playstate extends state{
    private static final int wallspacing = 125;
    private static final int wallcount = 20;
    private static final int groundoffset = -50;
    private bird birdy;
    private Texture bg;
    private Texture ground;
    private Vector2 groundposition1, groundposition2;
    private Array<wall> walls;
    public int sccounter;
    public Music music;

    public playstate(GameStateManager gsm) {
        super(gsm);
        birdy = new bird(50,300);
        camera.setToOrtho(false, FlappyBird.width/2, FlappyBird.height/2);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundposition1 = new Vector2(camera.position.x - camera.viewportWidth/2, groundoffset);
        groundposition2 = new Vector2((camera.position.x - camera.viewportWidth/2) + ground.getWidth(), groundoffset);
        walls = new Array<wall>();
        for(int i=1; i<=wallcount; i++){
            walls.add(new wall(i * (wallspacing + wall.wallwidth)));
        }
        sccounter = 0;
        music = Gdx.audio.newMusic(Gdx.files.internal("fireaura.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        font.getData().setScale(2);
        font.setUseIntegerPositions(false);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            birdy.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateground();
        birdy.update(dt);
        camera.position.x = birdy.getPosition().x + 80;

        for(int i=0; i<walls.size; i++){
            wall zid = walls.get(i);
            if(camera.position.x - camera.viewportWidth/2 > zid.getPositiontop().x + zid.getTop().getWidth()){
                zid.reposition(zid.getPositiontop().x + ((zid.wallwidth + wallspacing) * wallcount));
            }
            if(zid.collides(birdy.getBirdlimit())){
                FlappyBird.counter = sccounter;
                gsm.set(new gameoverstate(gsm));
            }
            if (zid.scorecounter(birdy.getBirdlimit()) && zid.getCheck() == 0){
                sccounter += 1;
                zid.setCheck(1);
            }
        }
        if(birdy.getPosition().y <= ground.getHeight() + groundoffset){
            FlappyBird.counter = sccounter;
            gsm.set(new gameoverstate(gsm));
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg,camera.position.x - camera.viewportWidth / 2,0);
        sb.draw(birdy.getBirdy(),birdy.getPosition().x,birdy.getPosition().y);
        //sa rotacijom
        //sb.draw(birdy.getBirdy(), birdy.getPosition().x, birdy.getPosition().y, birdy.getBirdy().getRegionWidth()/2, birdy.getBirdy().getRegionHeight()/2, birdy.getBirdy().getRegionWidth(), birdy.getBirdy().getRegionHeight(), 1, 1, birdy.getRotation());
        for(wall zid : walls) {
            sb.draw(zid.getTop(), zid.getPositiontop().x, zid.getPositiontop().y);
            sb.draw(zid.getBottom(), zid.getPositionbottom().x, zid.getPositionbottom().y);
        }
        sb.draw(ground, groundposition1.x, groundposition1.y);
        sb.draw(ground, groundposition2.x, groundposition2.y);
        font.draw(sb, String.valueOf(sccounter), camera.position.x - 5, camera.viewportHeight + groundoffset);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        birdy.dispose();
        ground.dispose();
        for(wall zid : walls){
            zid.dispose();
        }
        font.dispose();
        music.dispose();
    }

    private void updateground(){
        if(camera.position.x - camera.viewportWidth/2 > groundposition1.x + ground.getWidth()){
            groundposition1.add(ground.getWidth()*2, 0);
        }
        if(camera.position.x - camera.viewportWidth/2 > groundposition2.x + ground.getWidth()){
            groundposition2.add(ground.getWidth()*2, 0);
        }
    }
}
