/*
 * Copyright (C) 2022 - 2026. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hotstone.main;

import hotstone.doubles.FakeObjectGame;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;
import hotstone.standard.factories.*;
import hotstone.view.core.HotStoneDrawingType;
import hotstone.view.core.HotStoneFactory;
import hotstone.view.tool.HotSeatStateTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.SelectionTool;

/** A single jvm application which uses a 'hotseat' to allow both players to
 * alternate play.
 */
public class HotSeatStone {
  public static void main(String[] args) {
    Game game;

    System.out.println("=== Starting HotSeat on game variant: " + args[0] + " ===");
    switch (args[0]){
      case "alphastone":
        game = new StandardHotStoneGame(new AlphaStoneFactory());
        break;
      case "betastone":
        game = new StandardHotStoneGame(new BetaStoneFactory());
        break;
      case "deltastone":
        game = new StandardHotStoneGame(new DeltaStoneFactory());
        break;
      case "epsilonstone":
        game = new StandardHotStoneGame(new EpsilonStoneFactory());
        break;
      case "etastone":
        game = new StandardHotStoneGame(new EtaStoneFactory());
        break;
      case "gammastone":
        game = new StandardHotStoneGame(new GammaStoneFactory());
        break;
      case "semistone":
        game = new StandardHotStoneGame(new SemiStoneFactory());
        break;
      case "zetastone":
        game = new StandardHotStoneGame(new ZetaStoneFactory());
        break;
      default:
        throw new IllegalArgumentException("Unknown game variant: " + args[0]);

    }
    DrawingEditor editor =
            new MiniDrawApplication( "HotSeat: Variant " + args[0],
                    new HotStoneFactory(game, Player.FINDUS,
                            HotStoneDrawingType.HOTSEAT_MODE) );
    editor.open();
    editor.setTool(new HotSeatStateTool(editor, game));
  }
}
