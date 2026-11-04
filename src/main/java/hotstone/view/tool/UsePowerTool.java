package hotstone.view.tool;

import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.standard.StandardHero;
import hotstone.view.figure.HeroFigure;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;
import java.util.Objects;

public class UsePowerTool extends NullTool {
    private DrawingEditor editor;
    private Game game;

    public UsePowerTool(DrawingEditor editor, Game game){
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y){
        Drawing model = editor.drawing();
        Figure figureAtPosition = model.findFigure(x,y);
        HeroFigure selectedHeroFig = (HeroFigure) figureAtPosition;
        Hero selectedHero = selectedHeroFig.getAssociatedHero();
        if(Objects.equals(game.getHero(game.getPlayerInTurn()).getOwner(), selectedHero.getOwner()))
        {
            game.usePower(game.getPlayerInTurn());
        }
    }
}
