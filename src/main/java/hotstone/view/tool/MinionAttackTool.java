package hotstone.view.tool;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.view.figure.CardFigure;
import hotstone.view.figure.HeroFigure;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.ZOrder;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class MinionAttackTool extends NullTool {
    private DrawingEditor editor;
    private Game game;
    private CardFigure draggedActor;
    private int lastX;
    private int lastY;
    private int orgX;
    private int orgY;
    private Player whoAmIPlaying;

    public MinionAttackTool(DrawingEditor editor, Game game, Player whoAmIPlaying) {
        this.editor = editor;
        this.game = game;
        this.whoAmIPlaying = whoAmIPlaying;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        Drawing model = editor.drawing();
        // Note: The HotSeatStateTool should ensure that this tool
        // is only active iff there is a card figure below (x,y)
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        draggedActor = (CardFigure) figureAtPosition;
        // Move the card to the visual top
        model.zOrder(draggedActor, ZOrder.TO_TOP);
        // And remember where the card was dragged from (orgX, orgY)
        lastX = x; lastY = y;
        orgX = x; orgY = y;
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        // compute relative movement
        draggedActor.moveBy(x - lastX, y - lastY);
        // update last position
        lastX = x; lastY = y;
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        if (draggedActor != null) {
            Drawing model = editor.drawing();
            Card attacker = draggedActor.getAssociatedCard();

            draggedActor.moveBy(10000, 10000);

            Figure targetFigure = model.findFigure(e.getX(), e.getY());

            draggedActor.moveBy(-10000, -10000);

            Status status = Status.NOT_PLAYER_IN_TURN;

            if (targetFigure instanceof CardFigure) {
                Card targetCard = ((CardFigure) targetFigure).getAssociatedCard();
                status = game.attackCard(whoAmIPlaying, attacker, targetCard);

            } else if (targetFigure instanceof HeroFigure) {
                status = game.attackHero(whoAmIPlaying, attacker);
            }


            draggedActor.moveBy(orgX - x, orgY - y);

            editor.showStatus("Attack result: " + status);
            draggedActor = null;
        }
    }
}

