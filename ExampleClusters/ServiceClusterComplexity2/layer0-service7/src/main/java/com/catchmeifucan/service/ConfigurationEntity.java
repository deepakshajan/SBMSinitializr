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
package com.catchmeifucan.service;

import org.springframework.context.annotation.Bean;

/**
 * Entity class that serves the purpose of holding the various configuration values
 * @author Deepak Shajan
 * @see <text>config.json and canvasLayout.txt</text>
 */
class ConfigurationEntity {

    /**
     * This 2-D matrix is used to represents the paths of the arena.
     * <p>A 2 dimentional matrix of dimensions {@link #canvasDivX} * {@link #canvasDivY}.
     * The paths drawn in the playing area are rendered along the <b>1's</b> of this matrix.All the <b>0's</b> represent restricted area.</p>
     */
    private String[][] canvasLayout;

    /**
     * The width of the canvas in pixels.
     */
    private int canvasWidth;
    /**
     * The height of the canvas in pixels.
     */
    private int canvasHeight;
    /**
     * The number of divisions made along the width of the canvas.
     */
    private int canvasDivX;
    /**
     * The number of divisions made along the height of the canvas.
     */
    private int canvasDivY;
    /**
     * Colour of the restricted area in the canvas.
     */
    private String canvasRestrictedColour;

    /**
     * Colour of the game piece, ie the piece that the user controls.
     */
    private String gamePieceColour;
    /**
     * Radius of the game piece.
     */
    private int gamePieceRadius;
    /**
     * The starting position of the game piece.<p>eg <b>{110,70}</b> -> creates a game piece having its center coordinates <b>(110,70)</b>.</p>
     */
    private int[] gamePieceStartPosition;
    /**
     * The number of pixels in any direction moved by the game piece.
     */
    private int gamePieceStep;
    /**
     * Time in milliseconds between each game piece step.
     */
    private int gamePieceStepInterval;
    /**
     * A buffer value kept to optimize the game piece size.
     * <p><b>Note : Be very cautious in changing this value. Changing this may have huge impacts in the application</b></p>
     */
    private float gamePieceSquareBuffer;

    /**
     * Colour of the opponent piece.
     */
    private String opponentPieceColour;
    /**
     * Radius of the opponent piece.
     */
    private int opponentPieceRadius;
    /**
     * The starting position of the opponent piece.<p>eg <b>{110,70}</b> -> creates an opponent piece having its center coordinates <b>(110,70)</b>.</p>
     */
    private int[][] opponentPieceStart = {{10, 10}, {10, 490}, {490, 10}, {490, 490}};
    /**
     * The number of pixels in any direction moved by the game piece.
     */
    private int opponentPieceStep;
    /**
     * Time in milliseconds between each game piece step.
     */
    private int opponentPieceStepInterval;
    /**
     * Represents what percentage of total movements by the opponents is a totally random move.
     * <p>For example, if set to <code>50</code>, half of all the movements will be random. The other half of the movements will be based on the best computed direction.</p>
     */
    private int randomMoveProbability;

    /**
     * Time intervals(in milliseconds) at which the a check is performed for a catch.
     * <p>For example, if set to <code>1000</code>, then a check for catch is performed after every second. A catch is when an opponent piece collides with the game piece</p>
     */
    private int catchCheckInterval;
    /**
     * Time intervals(in milliseconds) after which the score is incremented by one.
     * <p>For example, if set to <code>1000</code>, then score is increased after every second.
     */
    private int scoreChangeInterval;
    /**
     * The score after which the level is incremented.
     */
    private int levelChangeScore;
    /**
     * The rate of increase in speed after each level increment.
     */
    private int levelUpSpeedChangeRate;
    /**
     * An approximate value which represents the highest possible value that can be used as a configuration value.
     */
    private int highValue;


    public String[][] getCanvasLayout() { return canvasLayout; }

    public void setCanvasLayout(String[][] canvasLayout) { this.canvasLayout = canvasLayout; }

    public int getCanvasWidth() { return canvasWidth; }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public int getCanvasDivX() {
        return canvasDivX;
    }

    public void setCanvasDivX(int canvasDivX) {
        this.canvasDivX = canvasDivX;
    }

    public int getCanvasDivY() {
        return canvasDivY;
    }

    public void setCanvasDivY(int canvasDivY) {
        this.canvasDivY = canvasDivY;
    }

    public String getCanvasRestrictedColour() {
        return canvasRestrictedColour;
    }

    public void setCanvasRestrictedColour(String canvasRestrictedColour) {
        this.canvasRestrictedColour = canvasRestrictedColour;
    }

    public String getGamePieceColour() {
        return gamePieceColour;
    }

    public void setGamePieceColour(String gamePieceColour) {
        this.gamePieceColour = gamePieceColour;
    }

    public int getGamePieceRadius() {
        return gamePieceRadius;
    }

    public void setGamePieceRadius(int gamePieceRadius) {
        this.gamePieceRadius = gamePieceRadius;
    }

    public int[] getGamePieceStartPosition() {
        return gamePieceStartPosition;
    }

    public void setGamePieceStartPosition(int[] gamePieceStartPosition) {
        this.gamePieceStartPosition = gamePieceStartPosition;
    }

    public int getGamePieceStep() {
        return gamePieceStep;
    }

    public void setGamePieceStep(int gamePieceStep) {
        this.gamePieceStep = gamePieceStep;
    }

    public int getGamePieceStepInterval() {
        return gamePieceStepInterval;
    }

    public void setGamePieceStepInterval(int gamePieceStepInterval) {
        this.gamePieceStepInterval = gamePieceStepInterval;
    }

    public float getGamePieceSquareBuffer() {
        return gamePieceSquareBuffer;
    }

    public void setGamePieceSquareBuffer(float gamePieceSquareBuffer) {
        this.gamePieceSquareBuffer = gamePieceSquareBuffer;
    }

    public String getOpponentPieceColour() {
        return opponentPieceColour;
    }

    public void setOpponentPieceColour(String opponentPieceColour) {
        this.opponentPieceColour = opponentPieceColour;
    }

    public int getOpponentPieceRadius() {
        return opponentPieceRadius;
    }

    public void setOpponentPieceRadius(int opponentPieceRadius) {
        this.opponentPieceRadius = opponentPieceRadius;
    }

    public int[][] getOpponentPieceStart() {
        return opponentPieceStart;
    }

    public void setOpponentPieceStart(int[][] opponentPieceStart) {
        this.opponentPieceStart = opponentPieceStart;
    }

    public int getOpponentPieceStep() {
        return opponentPieceStep;
    }

    public void setOpponentPieceStep(int opponentPieceStep) {
        this.opponentPieceStep = opponentPieceStep;
    }

    public int getOpponentPieceStepInterval() {
        return opponentPieceStepInterval;
    }

    public void setOpponentPieceStepInterval(int opponentPieceStepInterval) {
        this.opponentPieceStepInterval = opponentPieceStepInterval;
    }

    public int getRandomMoveProbability() {
        return randomMoveProbability;
    }

    public void setRandomMoveProbability(int randomMoveProbability) {
        this.randomMoveProbability = randomMoveProbability;
    }

    public int getCatchCheckInterval() {
        return catchCheckInterval;
    }

    public void setCatchCheckInterval(int catchCheckInterval) {
        this.catchCheckInterval = catchCheckInterval;
    }

    public int getScoreChangeInterval() {
        return scoreChangeInterval;
    }

    public void setScoreChangeInterval(int scoreChangeInterval) {
        this.scoreChangeInterval = scoreChangeInterval;
    }

    public int getLevelChangeScore() {
        return levelChangeScore;
    }

    public void setLevelChangeScore(int levelChangeScore) {
        this.levelChangeScore = levelChangeScore;
    }

    public int getLevelUpSpeedChangeRate() {
        return levelUpSpeedChangeRate;
    }

    public void setLevelUpSpeedChangeRate(int levelUpSpeedChangeRate) {
        this.levelUpSpeedChangeRate = levelUpSpeedChangeRate;
    }

    public int getHighValue() {
        return highValue;
    }

    public void setHighValue(int highValue) {
        this.highValue = highValue;
    }

}
