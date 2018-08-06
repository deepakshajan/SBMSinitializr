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


import com.catchmeifucan.CatchMeIfUCanApplication;

/**
 * POJO class to hold all the configurations required to initialize the game.
 * <p> Refer to the files <b>configuration.json</b> and <b>canvasLayout.txt</b> to see the configurations.
 * An object of this class which is enriched with the configurations is registered as a bean with singleton scope in the spring container.
 * The bean mentioned is registered with name '{@code configuration}'.Refer {@link CatchMeIfUCanApplication#configuration()} for the bean configuration.
 * @author Deepak Shajan
 *
 */
public class ConfigurationEntity {
	
	
	/**
	 * A two dimensional matrix having binary values, represents the layout of the canvas area.
	 * <p>Each index position of the matrix can hold only either <b>1</b> or <b>0</b>.
	 */
	private char[][] canvasLayout;
	
	private int canvasWidth;
	private int canvasHeight;
	private int canvasDivX;
	private int canvasDivY;
	private String canvasRestrictedColour;

	private String gamePieceColour;
	private int gamePieceRadius;
	private int[] gamePieceStartPosition;
	private int gamePieceStep;
	private int gamePieceStepInterval;
	private float gamePieceSquareBuffer;

	private String opponentPieceColour;
	private int opponentPieceRadius;
	private int[][] opponentPieceStart = {{10,10},{10,490},{490,10},{490,490}};
	private int opponentPieceStep;
	private int opponentPieceStepInterval;
	private int randomMoveProbability;

	private int catchCheckInterval;
	private int scoreChangeInterval; 
	private int levelChangeScore;
	private int levelUpSpeedChangeRate;
	private int highValue;
	
	
	public char[][] getCanvasLayout() {
		return canvasLayout;
	}
	public void setCanvasLayout(char[][] canvasLayout) {
		this.canvasLayout = canvasLayout;
	}
	public int getCanvasWidth() {
		return canvasWidth;
	}
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
