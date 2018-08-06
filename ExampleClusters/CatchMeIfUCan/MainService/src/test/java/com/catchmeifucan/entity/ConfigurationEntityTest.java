/**
 * MIT License
 Copyright (c) 2017 deepakshajan
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

package com.catchmeifucan.entity;

import com.catchmeifucan.utils.JUnitErrorConstants;
import com.catchmeifucan.utils.JUnitUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;


/**
 * Unit test cases for class {@link ConfigurationEntity}
 * <p>Only to be used as a reference</p>
 * @author Deepak Shajan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigurationEntityTest extends JUnitUtils {

    @MockBean
    private ConfigurationEntity configuration;

    @Before
    public void setUp() {
        Mockito.when(configuration.getCanvasHeight()).thenReturn(500);
        Mockito.when(configuration.getCanvasWidth()).thenReturn(500);
        Mockito.when(configuration.getCanvasDivX()).thenReturn(25);
        Mockito.when(configuration.getCanvasDivY()).thenReturn(25);
        Mockito.when(configuration.getCanvasRestrictedColour()).thenReturn("silver");
        Mockito.when(configuration.getGamePieceColour()).thenReturn("red");
        Mockito.when(configuration.getGamePieceRadius()).thenReturn(9);
        Mockito.when(configuration.getGamePieceStartPosition()).thenReturn(new int[]{250,250});
        Mockito.when(configuration.getGamePieceStep()).thenReturn(5);
        Mockito.when(configuration.getGamePieceStepInterval()).thenReturn(75);
        Mockito.when(configuration.getGamePieceSquareBuffer()).thenReturn(1.001f);
        Mockito.when(configuration.getOpponentPieceColour()).thenReturn("black");
        Mockito.when(configuration.getOpponentPieceRadius()).thenReturn(9);
        Mockito.when(configuration.getOpponentPieceStart()).thenReturn(new int[][]{{10,10},{10,490},{490,10},{490,490}});
        Mockito.when(configuration.getOpponentPieceStep()).thenReturn(5);
        Mockito.when(configuration.getOpponentPieceStepInterval()).thenReturn(75);
        Mockito.when(configuration.getRandomMoveProbability()).thenReturn(10);
        Mockito.when(configuration.getCatchCheckInterval()).thenReturn(10);
        Mockito.when(configuration.getScoreChangeInterval()).thenReturn(250);
        Mockito.when(configuration.getLevelChangeScore()).thenReturn(25);
        Mockito.when(configuration.getLevelUpSpeedChangeRate()).thenReturn(5);
        Mockito.when(configuration.getHighValue()).thenReturn(10000);
        Mockito.when(configuration.getCanvasLayout()).thenReturn(new char[][]{{1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                                                             {1,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,1,0,1,0,0,1},
                                                                             {1,0,1,1,1,1,0,0,1,0,0,1,1,1,1,0,0,0,0,1,0,1,1,1,1},
                                                                             {1,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,1},
                                                                             {1,0,1,0,0,1,1,1,1,0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1},
                                                                             {1,1,1,0,0,1,0,0,1,0,0,1,1,0,0,0,1,0,0,1,0,0,0,0,1},
                                                                             {1,0,1,0,0,1,0,0,1,0,0,0,1,0,0,0,1,1,0,1,0,1,1,1,1},
                                                                             {1,0,1,0,0,1,0,0,1,1,0,0,1,0,0,0,0,1,0,1,0,1,0,0,0},
                                                                             {1,0,1,1,0,1,0,0,0,1,0,0,1,0,0,0,0,1,0,1,1,1,0,0,0},
                                                                             {1,0,0,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,0,1,0,1,0,0,0},
                                                                             {1,0,0,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,0,1,0,1,1,1,1},
                                                                             {1,1,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,0,1,0,0,0,0,1},
                                                                             {1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
                                                                             {1,0,0,1,0,1,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,1,1,1},
                                                                             {1,0,0,1,0,1,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1},
                                                                             {1,1,1,1,0,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
                                                                             {1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,1},
                                                                             {1,1,1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,1},
                                                                             {0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1},
                                                                             {1,1,1,0,1,0,1,0,0,1,0,0,1,0,0,0,1,0,0,0,1,0,1,0,1},
                                                                             {1,0,1,0,1,0,1,1,1,1,0,0,1,0,0,0,1,0,0,0,1,0,1,1,1},
                                                                             {1,0,1,0,1,0,1,0,0,1,0,0,1,1,1,1,1,1,1,0,1,0,0,0,1},
                                                                             {1,0,1,1,1,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,1,1,1,0,1},
                                                                             {1,0,0,0,0,0,1,0,0,1,0,0,1,0,1,1,1,0,1,0,1,0,1,0,1},
                                                                             {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1}});
    }

    @Test
    public void testCanvasWidth() {

        int width = configuration.getCanvasWidth();
        Assert.isInstanceOf(Integer.class,width, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(width), JUnitErrorConstants.VALUE);
        Assert.isTrue(width == configuration.getCanvasHeight(), JUnitErrorConstants.DEPENDANCY);
        Assert.isTrue(width> configuration.getCanvasDivX() && width% configuration.getCanvasDivX()==0, JUnitErrorConstants.VALUE);
    }

    @Test
    public void testCanvasHeight() {

        int height = configuration.getCanvasHeight();
        Assert.isInstanceOf(Integer.class,height, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(height), JUnitErrorConstants.VALUE);
        Assert.isTrue(height == configuration.getCanvasHeight(), JUnitErrorConstants.DEPENDANCY);
        Assert.isTrue(height> configuration.getCanvasDivY() && height% configuration.getCanvasDivY()==0, JUnitErrorConstants.VALUE);
    }

    @Test
    public void testCanvasDivX() {

        int x = configuration.getCanvasDivX();
        Assert.isInstanceOf(Integer.class, x, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(x), JUnitErrorConstants.VALUE);
        Assert.isTrue(x == configuration.getCanvasDivY(), JUnitErrorConstants.DEPENDANCY);
    }

    @Test
    public void testCanvasDivY() {

        int y = configuration.getCanvasDivY();
        Assert.isInstanceOf(Integer.class, y, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(y), JUnitErrorConstants.VALUE);
        Assert.isTrue(y == configuration.getCanvasDivX(), JUnitErrorConstants.DEPENDANCY);
    }

    @Test
    public void testCanvasRestrictedColour() {

        String colour = configuration.getCanvasRestrictedColour();
        Assert.isTrue(null != colour, JUnitErrorConstants.NULL);
        Assert.isInstanceOf(String.class, colour, JUnitErrorConstants.TYPE);
        Assert.isTrue(isSupportedColour(colour), JUnitErrorConstants.VALUE);
    }

    @Test
    public void testGamePieceColour() {

        String colour = configuration.getGamePieceColour();
        Assert.isTrue(null != colour, JUnitErrorConstants.NULL);
        Assert.isInstanceOf(String.class, colour, JUnitErrorConstants.TYPE);
        Assert.isTrue(isSupportedColour(colour), JUnitErrorConstants.VALUE);
        Assert.isTrue(!colour.equalsIgnoreCase(configuration.getOpponentPieceColour()), JUnitErrorConstants.DEPENDANCY);
    }

    @Test
    public void testGamePieceRadius() {

        int radius = configuration.getGamePieceRadius();
        Assert.isInstanceOf(Integer.class, radius, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(radius), JUnitErrorConstants.VALUE);
        Assert.isTrue(radius<(configuration.getCanvasWidth()/ configuration.getCanvasDivX()), JUnitErrorConstants.DEPENDANCY);
    }

    @Test
    public void testGamePieceStartPosition() {

        int[] position = configuration.getGamePieceStartPosition();
        Assert.isInstanceOf(int[].class, position, JUnitErrorConstants.TYPE);
        Assert.isTrue(position[0]>=0&&position[0]<= configuration.getCanvasHeight(), JUnitErrorConstants.DEPENDANCY);
        Assert.isTrue(position[1]>=0&&position[1]<= configuration.getCanvasWidth(), JUnitErrorConstants.DEPENDANCY);
    }

    @Test
    public void testGamePieceStep() {

        int step = configuration.getGamePieceStep();
        Assert.isInstanceOf(Integer.class, step, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(step), JUnitErrorConstants.DEPENDANCY);
        Assert.isTrue(step<= configuration.getGamePieceRadius(), JUnitErrorConstants.DEPENDANCY);
    }

    @Test
    public void testGamePieceStepInterval() {

        int interval = configuration.getGamePieceStepInterval();
        Assert.isInstanceOf(Integer.class, interval, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(interval), JUnitErrorConstants.VALUE);
    }

    @Test
    public void testGamePieceSquareBuffer() {

        float buffer = configuration.getGamePieceSquareBuffer();
        Assert.isInstanceOf(Float.class, buffer, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(buffer), JUnitErrorConstants.VALUE);
    }

    @Test
    public void testOpponentPieceColour() {

        String colour = configuration.getOpponentPieceColour();
        Assert.isTrue(null != colour, JUnitErrorConstants.NULL);
        Assert.isInstanceOf(String.class, colour, JUnitErrorConstants.TYPE);
        Assert.isTrue(isSupportedColour(colour), JUnitErrorConstants.VALUE);
        Assert.isTrue(!colour.equalsIgnoreCase(configuration.getGamePieceColour()), JUnitErrorConstants.DEPENDANCY);
    }

    @Test
    public void testOpponentPieceRadius() {

        int radius = configuration.getOpponentPieceRadius();
        Assert.isInstanceOf(Integer.class, radius, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(radius), JUnitErrorConstants.VALUE);
        Assert.isTrue(radius<(configuration.getCanvasWidth()/ configuration.getCanvasDivX()), JUnitErrorConstants.DEPENDANCY);
    }

    @Test
    public void testOpponentPieceStartPosition() {

        int[][] positions = configuration.getOpponentPieceStart();
        int [] gamePieceStartPosition = configuration.getGamePieceStartPosition();
        Assert.isTrue(null != positions, JUnitErrorConstants.NULL);
        Assert.isInstanceOf(int[][].class, positions, JUnitErrorConstants.TYPE);
        for(int[] position : positions) {
            Assert.isTrue(position[0]>=0&&position[0]<= configuration.getCanvasHeight(), JUnitErrorConstants.DEPENDANCY);
            Assert.isTrue(position[1]>=0&&position[1]<= configuration.getCanvasWidth(), JUnitErrorConstants.DEPENDANCY);
            Assert.isTrue((position[0]==gamePieceStartPosition[0]?(position[1]!=gamePieceStartPosition[1]?false:true):true), JUnitErrorConstants.DEPENDANCY);
        }
    }

    @Test
    public void testOpponentPieceStep() {

        int step = configuration.getOpponentPieceStep();
        Assert.isInstanceOf(Integer.class, step, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(step), JUnitErrorConstants.DEPENDANCY);
        Assert.isTrue(step<= configuration.getOpponentPieceRadius(), JUnitErrorConstants.DEPENDANCY);
    }

    @Test
    public void testOpponentPieceStepInterval() {

        int interval = configuration.getGamePieceStepInterval();
        Assert.isInstanceOf(Integer.class, interval, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(interval), JUnitErrorConstants.VALUE);
    }

    @Test
    public void testRandomMoveProbability() {

        int probablity = configuration.getRandomMoveProbability();
        Assert.isInstanceOf(Integer.class, probablity, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsideValidPercentageValueRange(probablity), JUnitErrorConstants.VALUE);
    }

    @Test
    public void testCatchCheckInterval() {

        int interval = configuration.getCatchCheckInterval();
        Assert.isInstanceOf(Integer.class, interval, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(interval), JUnitErrorConstants.VALUE);
    }

    @Test
    public void testScoreChangeInterval() {

        int interval = configuration.getScoreChangeInterval();
        Assert.isInstanceOf(Integer.class, interval, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(interval), JUnitErrorConstants.VALUE);
    }

    @Test
    public void testLevelChangeScore() {

        int score = configuration.getLevelChangeScore();
        Assert.isInstanceOf(Integer.class, score, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(score), JUnitErrorConstants.VALUE);
    }

    @Test
    public void testLevelUpSpeedChangeRate() {

        int rate = configuration.getLevelUpSpeedChangeRate();
        Assert.isInstanceOf(Integer.class, rate, JUnitErrorConstants.TYPE);
        Assert.isTrue(isInsidePositiveApplicationValueRange(rate), JUnitErrorConstants.VALUE);
    }

    @Test
    public void testHighValue() {

        int value = configuration.getHighValue();
        Assert.isInstanceOf(Integer.class, value, JUnitErrorConstants.TYPE);
        Assert.isTrue(value>0, JUnitErrorConstants.VALUE);
    }

    @Test
    public void testCanvasLayout() {

        char[][] layout = this.configuration.getCanvasLayout();
        Assert.isTrue(null != layout, JUnitErrorConstants.NULL);
        Assert.isInstanceOf(char[][].class, layout, JUnitErrorConstants.TYPE);
        Assert.isTrue(layout.length == configuration.getCanvasDivY(), JUnitErrorConstants.VALUE);
        Assert.isTrue(layout[0].length == configuration.getCanvasDivX(), JUnitErrorConstants.VALUE);
    }

}
