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

let assert = require('chai').assert;
let expect = require('chai').expect;
let sinon = require('sinon');

let clock = null;
let documentStub = null;

let nmsp = require('../../../../main/resources/static/js/game'); //The global namespace exported from game.js
let testConfig = { // These are the test configurations
    "canvasLayout": [["1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"],
        ["1", "0", "0", "0", "0", "1", "0", "0", "1", "0", "0", "1", "0", "0", "1", "0", "0", "0", "0", "1", "0", "1", "0", "0", "1"],
        ["1", "0", "1", "1", "1", "1", "0", "0", "1", "0", "0", "1", "1", "1", "1", "0", "0", "0", "0", "1", "0", "1", "1", "1", "1"],
        ["1", "0", "1", "0", "0", "1", "0", "0", "1", "0", "0", "1", "0", "0", "1", "0", "0", "0", "0", "1", "0", "0", "0", "0", "1"],
        ["1", "0", "1", "0", "0", "1", "1", "1", "1", "0", "0", "1", "0", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"],
        ["1", "1", "1", "0", "0", "1", "0", "0", "1", "0", "0", "1", "1", "0", "0", "0", "1", "0", "0", "1", "0", "0", "0", "0", "1"],
        ["1", "0", "1", "0", "0", "1", "0", "0", "1", "0", "0", "0", "1", "0", "0", "0", "1", "1", "0", "1", "0", "1", "1", "1", "1"],
        ["1", "0", "1", "0", "0", "1", "0", "0", "1", "1", "0", "0", "1", "0", "0", "0", "0", "1", "0", "1", "0", "1", "0", "0", "0"],
        ["1", "0", "1", "1", "0", "1", "0", "0", "0", "1", "0", "0", "1", "0", "0", "0", "0", "1", "0", "1", "1", "1", "0", "0", "0"],
        ["1", "0", "0", "1", "1", "1", "0", "0", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "0", "1", "0", "0", "0"],
        ["1", "0", "0", "0", "0", "1", "0", "0", "0", "1", "0", "0", "1", "0", "0", "1", "0", "0", "0", "1", "0", "1", "1", "1", "1"],
        ["1", "1", "1", "0", "0", "1", "0", "0", "0", "1", "0", "0", "1", "0", "0", "1", "0", "0", "0", "1", "0", "0", "0", "0", "1"],
        ["1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1"],
        ["1", "0", "0", "1", "0", "1", "0", "0", "0", "1", "0", "0", "1", "0", "0", "1", "0", "0", "0", "0", "0", "0", "1", "1", "1"],
        ["1", "0", "0", "1", "0", "1", "0", "0", "0", "1", "0", "0", "1", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0", "0", "1"],
        ["1", "1", "1", "1", "0", "1", "1", "0", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1"],
        ["1", "0", "0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "1", "0", "0", "0", "1", "0", "1"],
        ["1", "1", "1", "0", "0", "0", "1", "0", "0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "1", "0", "0", "0", "1", "0", "1"],
        ["0", "0", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "0", "1"],
        ["1", "1", "1", "0", "1", "0", "1", "0", "0", "1", "0", "0", "1", "0", "0", "0", "1", "0", "0", "0", "1", "0", "1", "0", "1"],
        ["1", "0", "1", "0", "1", "0", "1", "1", "1", "1", "0", "0", "1", "0", "0", "0", "1", "0", "0", "0", "1", "0", "1", "1", "1"],
        ["1", "0", "1", "0", "1", "0", "1", "0", "0", "1", "0", "0", "1", "1", "1", "1", "1", "1", "1", "0", "1", "0", "0", "0", "1"],
        ["0", "0", "1", "1", "1", "0", "1", "0", "0", "1", "0", "0", "1", "0", "0", "0", "0", "0", "1", "0", "1", "1", "1", "0", "1"],
        ["1", "0", "0", "0", "0", "0", "1", "0", "0", "1", "0", "0", "1", "0", "1", "1", "1", "0", "1", "0", "1", "0", "1", "0", "1"],
        ["1", "1", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "0", "1", "1", "1"]],
    "canvasWidth": 500,
    "canvasHeight": 500,
    "canvasDivX": 25,
    "canvasDivY": 25,
    "canvasRestrictedColour": "silver",

    "gamePieceColour": "red",
    "gamePieceRadius": 9,
    "gamePieceStartPosition": [250, 250],
    "gamePieceStep": 5,
    "gamePieceStepInterval": 75,
    "gamePieceSquareBuffer": 1.001,

    "opponentPieceColour": "black",
    "opponentPieceRadius": 9,
    "opponentPieceStart": [[10, 10], [10, 490], [490, 10], [490, 490]],
    "opponentPieceStep": 5,
    "opponentPieceStepInterval": 75,
    "randomMoveProbability": 10,

    "catchCheckInterval": 10,
    "scoreChangeInterval": 250,
    "levelChangeScore": 25,
    "levelUpSpeedChangeRate": 5,
    "highValue": 10000
};

/**
 * Gets executed before each test case.Equivalent to setUp method of JUnits.
 */
beforeEach(function () {
    global.window = {clearInterval: (timer) => {},setInterval:() => {}};
    global.document = {getElementById: (id) => {innerHTML:''}, addEventListener: (id, callback) => {}, removeEventListener: (id, callback) => {}};
    documentStub = sinon.stub(document, 'getElementById');
    let fakeCtx = {beginPath: () => {}, arc: () => {}, fillStyle: () => {}, fill: () => {}, stroke: () => {}, fillRect: () => {}, closePath: () => {}, clearRect: () => {}};
    let fakeCanvas = {getContext: (id) => fakeCtx};
    let fakeElement = {innerHTML:''};
    documentStub.withArgs('canvas').returns(fakeCanvas);
    documentStub.withArgs('levelBoard').returns(fakeElement);
    documentStub.withArgs('scoreBoard').returns(fakeElement);
    documentStub.withArgs('headingText').returns(fakeElement);
    clock = sinon.useFakeTimers();
    nmsp.init(testConfig);
});
/**
 * Gets executed after each test case.Equivalent to tearDown method of JUnits.
 */
afterEach(function () {
    clock.restore();
    documentStub.restore();
});


describe('Tests executed on the game.js file', function () {
    let config = nmsp.config;
    it('Should have a valid global namespace', function () {
        assert.isObject(nmsp);
    });

    describe('Test on the configuration values', function () {
        assertObject(config);
        describe('Test on the canvas layout configration', function () {
            let layout = config.canvasLayout;
            assertIs2DArray(layout, config.canvasDivY, config.canvasDivX, 'String');
            describe('Check for value', function () {
                it('Each element in array should either be "0" or "1"', function () {
                    for(let i=config.canvasDivY-1;i>=0;i--)
                        for(let j=config.canvasDivX-1;j>=0;j--)
                            expect(layout[i][j]).is.oneOf(["0","1"]);
                });
            });
        });
        describe('Test on the canvas width configuration', function () {
            let width = config.canvasWidth;
            assertNumber(width);
            it('Should be greater than divX', function () {
                assert.isAtLeast(width, config.canvasDivX);
            });
            it('Should be Divisible by divX', function () {
                expect(width%config.canvasDivX).to.equal(0);
            });
            it('Should be equal to canvas height', function () {
                expect(width).to.equal(config.canvasHeight);
            });
        });
        describe('Test on the canvas height configuration', function () {
            let height = config.canvasHeight;
            assertNumber(height);
            it('Should be greater than divY', function () {
                assert.isAtLeast(height, config.canvasDivY);
            });
            it('Should be Divisible by divY', function () {
                expect(height%config.canvasDivY).to.equal(0);
            });
            it('Should be equal to canvas width', function () {
                expect(height).to.equal(config.canvasWidth);
            });
        });
        describe('Test on the canvas divX value', function () {
            let x = config.canvasDivX;
            assertNumber(x);
            it('Should perfectly divide width', function () {
                expect(config.canvasWidth%x).to.equal(0);
            });
            it('Should be equal to divY', function () {
                expect(x).to.equal(config.canvasDivY);
            });
        });
        describe('Test on the canvas divY value', function () {
            let y = config.canvasDivX;
            assertNumber(y);
            it('Should perfectly divide height', function () {
                expect(config.canvasHeight%y).to.equal(0);
            });
            it('Should be equal to divX', function () {
                expect(y).to.equal(config.canvasDivX);
            });
        });
        describe('Test on canvas Restricted Area colour', function () {
            assertColour(config.canvasRestrictedColour);
        });
        describe('Test on game piece colour', function () {
            let colour = config.gamePieceColour;
            assertColour(colour);
            it('Should not be same as opponent colour', function () {
                expect(colour).to.not.equal(config.opponentPieceColour);
            });
            it('Should not be same as canvas restricted colour', function () {
                expect(colour).to.not.equal(config.canvasRestrictedColour);
            });
        });
        describe('Test on game piece radius', function () {
            let radius = config.gamePieceRadius;
            assertNumber(radius);
            it('Should be less than the unit length', function () {
                expect(radius).lessThan(config.canvasWidth/config.canvasDivX);
            });
        });
        describe('Test on game piece start position', function () {
            let pos = config.gamePieceStartPosition;
            assertIs1DArray(pos, 2, 'number');
            assertInsidePlayArea(pos);
        });
        describe('Test on game piece step distance', function () {
            let step = config.gamePieceStep;
            assertNumber(step);
            it('Should be less than gamePiece radius', function () {
                expect(step).to.be.lessThan(config.gamePieceRadius);
            });
        });
        describe('Test on game piece step interval', function () {
            assertNumber(config.gamePieceStepInterval);
        });
        describe('Test on game piece square buffer', function () {
            assertNumber(config.gamePieceSquareBuffer);
        });
        describe('Test on opponent piece colour', function () {
            let colour = config.opponentPieceColour;
            assertColour(colour);
            it('Should not be same as game piece colour', function () {
                expect(colour).to.not.equal(config.gamePieceColour);
            });
            it('Should not be same as canvas restricted colour', function () {
                expect(colour).to.not.equal(config.canvasRestrictedColour);
            });
        });
        describe('Test on opponent piece radius', function () {
            let radius = config.opponentPieceRadius;
            assertNumber(radius);
            it('Should be less than the unit length', function () {
                expect(radius).lessThan(config.canvasWidth/config.canvasDivX);
            });
        });
        describe('Test on opponent piece start position', function () {
            let pos = config.opponentPieceStart;
            for (let i = pos.length-1; i >= 0; i--) {
                assertIs1DArray(pos[i], null, 'number');
                assertInsidePlayArea(pos[i]);
                it('Should not be same as game piece position', function () {
                    expect(pos[i][0]).to.not.equal(config.gamePieceStartPosition[0]);
                    expect(pos[i][1]).to.not.equal(config.gamePieceStartPosition[1]);
                });
            }
        });
        describe('Test on opponent piece step distance', function () {
            let step = config.opponentPieceStep;
            assertNumber(step);
            it('Should be less than opponent radius', function () {
                expect(step).to.be.lessThan(config.opponentPieceRadius);
            });
        });
        describe('Test on opponent piece step interval', function () {
            assertNumber(config.opponentPieceStepInterval);
        });
        describe('Test on random move probability', function () {
            assertPercentage(config.randomMoveProbability);
        });
        describe('Test on catch check interval', function () {
            assertNumber(config.catchCheckInterval);
        });
        describe('Test on score change interval', function () {
            assertNumber(config.scoreChangeInterval);
        });
        describe('Test on level change score', function () {
            assertNumber(config.levelChangeScore);
        });
        describe('Test on level up speed change rate', function () {
            assertNumber(config.levelUpSpeedChangeRate);
        });
        describe('Test on high value', function () {
            let value = config.highValue;
            it('Should be a number', function () {
                assert.isNumber(value);
            });
            it('Should be greater than zero', function () {
                expect(value).to.be.greaterThan(0);
            });
        });
    });

    describe('Tests on the javascript functions', function () {

        describe('Test on the functions bound to nmsp', function () {
            describe('Test on the nmsp.Piece() function', function () {
                it('Should return a new piece object', function () {
                    let piece = nmsp.Piece(config.gamePieceStartPosition[0],config.gamePieceStartPosition[1],config.gamePieceRadius,config.gamePieceColour);
                    assert.strictEqual(piece.x, config.gamePieceStartPosition[0]);
                    assert.strictEqual(piece.y, config.gamePieceStartPosition[1]);
                    assert.strictEqual(piece.radius, config.gamePieceRadius);
                    assert.strictEqual(piece.colour, config.gamePieceColour);
                });
            });
            describe('Test on the function nmsp.Piece.prototype.erase', function () {
                it('Should erase the piece from the canvas', function () {
                    let radius = nmsp.gamePiece.radius+nmsp.config.gamePieceSquareBuffer;
                    let diameter = 2*radius;
                    let stub = sinon.stub(nmsp.canvas, 'getContext');
                    stub.withArgs('2d').returns({clearRect:sinon.stub()});
                    nmsp.gamePiece.erase();
                    sinon.assert.callCount(nmsp.canvas.getContext('2d').clearRect.withArgs(nmsp.gamePiece.x-radius,nmsp.gamePiece.y-radius,diameter,diameter),1);
                    stub.restore(); // one way to avoid all the restore calls is to wrap the entire code in sinon.test() function
                });
            });
            describe('Test on the function nmsp.Piece.prototype.getRandomDirection', function () {
                it('Should return one among up,down,right or left', function () {
                    let directions = ['up','down','left','right'];
                    let gamePiece = nmsp.gamePiece;
                    for(let i=0;i<1000;i++) // random values are generated a 1000 times.Each time it should be one among up,down,left or right
                        expect(gamePiece.getRandomDirection()).be.oneOf(directions);
                });
                it('Should return all items from up,down,right or left at some point', function () {
                    let set = new Set();
                    let gamePiece = nmsp.gamePiece;
                    let direction = null;
                    for(let i=0;i<20;i++) {
                        if(set.size === 4)
                            break;
                        direction = gamePiece.getRandomDirection();
                        if(!set.has(direction))
                            set.add(direction);
                    }
                    assert.strictEqual(set.size,4);
                });
            });
            describe('Test on the function nmsp.Piece.prototype.getPossibleRandomDirection', function () {
                it('Case 1 : gamePiece in a cross junction position', function () {
                    let gamePiece = nmsp.gamePiece;
                    let possibleDirections = ['up','down','left','right'];
                    let set = new Set();
                    let direction = null;
                    for(let i=0;i<20;i++) {
                        assertDirection(gamePiece.getPossibleRandomDirection(),possibleDirections);
                        if(set.size === possibleDirections.length)
                            break;
                        direction = gamePiece.getPossibleRandomDirection();
                        if(!set.has(direction))
                            set.add(direction);
                    }
                    assert.strictEqual(set.size,possibleDirections.length);
                });
                it('Case 2 : gamePiece in a L junction', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x=10;gamePiece.y=490;
                    let possibleDirections = ['up','right'];
                    let set = new Set();
                    let direction = null;
                    for(let i=0;i<20;i++) {
                        assertDirection(gamePiece.getPossibleRandomDirection(),possibleDirections);
                        if(set.size === possibleDirections.length)
                            break;
                        direction = gamePiece.getPossibleRandomDirection();
                        if(!set.has(direction))
                            set.add(direction);
                    }
                    assert.strictEqual(set.size,possibleDirections.length);
                });
                it('Case 3 : gamePiece in a 7 junction', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x=490;gamePiece.y=10;
                    let possibleDirections = ['down','left'];
                    let set = new Set();
                    let direction = null;
                    for(let i=0;i<20;i++) {
                        assertDirection(gamePiece.getPossibleRandomDirection(),possibleDirections);
                        if(set.size === possibleDirections.length)
                            break;
                        direction = gamePiece.getPossibleRandomDirection();
                        if(!set.has(direction))
                            set.add(direction);
                    }
                    assert.strictEqual(set.size,possibleDirections.length);
                });
                it('Case 4 : gamePiece in a horizontal straight path', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x=30;gamePiece.y=10;
                    let possibleDirections = ['right','left'];
                    let set = new Set();
                    let direction = null;
                    for(let i=0;i<20;i++) {
                        assertDirection(gamePiece.getPossibleRandomDirection(),possibleDirections);
                        if(set.size === possibleDirections.length)
                            break;
                        direction = gamePiece.getPossibleRandomDirection();
                        if(!set.has(direction))
                            set.add(direction);
                    }
                    assert.strictEqual(set.size,possibleDirections.length);
                });
                it('Case 5 : gamePiece in a vertical straight path', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x=490;gamePiece.y=30;
                    let possibleDirections = ['up','down'];
                    let set = new Set();
                    let direction = null;
                    for(let i=0;i<20;i++) {
                        assertDirection(gamePiece.getPossibleRandomDirection(),possibleDirections);
                        if(set.size === possibleDirections.length)
                            break;
                        direction = gamePiece.getPossibleRandomDirection();
                        if(!set.has(direction))
                            set.add(direction);
                    }
                    assert.strictEqual(set.size,possibleDirections.length);
                });
                it('Case 6 : gamePiece in a |- junction', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x=130;gamePiece.y=410;
                    let possibleDirections = ['up','down','right'];
                    let set = new Set();
                    let direction = null;
                    for(let i=0;i<20;i++) {
                        assertDirection(gamePiece.getPossibleRandomDirection(),possibleDirections);
                        if(set.size === possibleDirections.length)
                            break;
                        direction = gamePiece.getPossibleRandomDirection();
                        if(!set.has(direction))
                            set.add(direction);
                    }
                    assert.strictEqual(set.size,possibleDirections.length);
                });
                it('Case 7 : gamePiece in a T junction', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x=190;gamePiece.y=370;
                    let possibleDirections = ['right','left','down'];
                    let set = new Set();
                    let direction = null;
                    for(let i=0;i<20;i++) {
                        assertDirection(gamePiece.getPossibleRandomDirection(),possibleDirections);
                        if(set.size === possibleDirections.length)
                            break;
                        direction = gamePiece.getPossibleRandomDirection();
                        if(!set.has(direction))
                            set.add(direction);
                    }
                    assert.strictEqual(set.size,possibleDirections.length);
                });
                it('Case 8 : gamePiece in a terminal(no route to right)', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x=30;gamePiece.y=490;
                    let possibleDirections = ['left'];
                    let set = new Set();
                    let direction = null;
                    for(let i=0;i<20;i++) {
                        assertDirection(gamePiece.getPossibleRandomDirection(),possibleDirections);
                        if(set.size === possibleDirections.length)
                            break;
                        direction = gamePiece.getPossibleRandomDirection();
                        if(!set.has(direction))
                            set.add(direction);
                    }
                    assert.strictEqual(set.size,possibleDirections.length);
                });
                it('Case 9 : gamePiece in a terminal(no route to top)', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x=10;gamePiece.y=470;
                    let possibleDirections = ['down'];
                    let set = new Set();
                    let direction = null;
                    for(let i=0;i<20;i++) {
                        assertDirection(gamePiece.getPossibleRandomDirection(),possibleDirections);
                        if(set.size === possibleDirections.length)
                            break;
                        direction = gamePiece.getPossibleRandomDirection();
                        if(!set.has(direction))
                            set.add(direction);
                    }
                    assert.strictEqual(set.size,possibleDirections.length);
                });
            });
            describe('Test on the function nmsp.Piece.prototype.movePieceTo', function () {
                it('Should update the properties of piece', function() {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.movePieceTo(150,150);
                    assert.strictEqual(gamePiece.x,150);
                    assert.strictEqual(gamePiece.y,150);
                    assert.strictEqual(gamePiece.ctx.fillStyle,nmsp.gamePiece.colour);
                });
                it('Should draw something on the canvas', function () {
                    let stubStroke = sinon.stub(nmsp.canvas.getContext('2d'),'stroke');
                    let stubFill = sinon.stub(nmsp.canvas.getContext('2d'),'fill');
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.movePieceTo(200,200);
                    sinon.assert.callCount(stubStroke,1);
                    sinon.assert.callCount(stubFill,1);
                    stubStroke.restore();
                    stubFill.restore();
                });
            });
            describe('Test on the function nmsp.Piece.prototype.takeOneStep', function () {
                it('Should return false for invalid directions', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x = 10;gamePiece.y= 10;
                    assert.isFalse(gamePiece.takeOneStep('left'));
                    assert.isFalse(gamePiece.takeOneStep('up'));
                    assert.isFalse(gamePiece.takeOneStep('invalidDirection'));
                });
                it('Should return true for valid directions', function () {
                    let gamePiece = nmsp.gamePiece;
                    assert.isTrue(gamePiece.takeOneStep('left'));
                    assert.isTrue(gamePiece.takeOneStep('right'));
                    assert.isTrue(gamePiece.takeOneStep('up'));
                    assert.isTrue(gamePiece.takeOneStep('down'));
                });
                it('Should update the gamePiece with the possible x,y coordinates for valid directions', function () {
                    let gamePiece = nmsp.gamePiece;
                    let orginalX = gamePiece.x, orginalY = gamePiece.y;
                    gamePiece.takeOneStep('left');
                    assert.strictEqual(gamePiece.x,orginalX-config.gamePieceStep);
                    assert.strictEqual(gamePiece.y,orginalY);
                    gamePiece.x = 250;gamePiece.y = 250;
                    gamePiece.takeOneStep('right');
                    assert.strictEqual(gamePiece.x,orginalX+config.gamePieceStep);
                    assert.strictEqual(gamePiece.y,orginalY);
                    gamePiece.x = 250;gamePiece.y = 250;
                    gamePiece.takeOneStep('up');
                    assert.strictEqual(gamePiece.x,orginalX);
                    assert.strictEqual(gamePiece.y,orginalY-config.gamePieceStep);
                    gamePiece.x = 250;gamePiece.y = 250;
                    gamePiece.takeOneStep('down');
                    assert.strictEqual(gamePiece.x,orginalX);
                    assert.strictEqual(gamePiece.y,orginalX+config.gamePieceStep);
                });
            });
            describe('Test on the function nmsp.Piece.prototype.isSetForReverse', function () {
                it('Should return true if set for reverse', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.currentDirection = 'left';
                    gamePiece.futureDirection = 'right';
                    assert.strictEqual(gamePiece.isSetForReverse(), true);
                    gamePiece.currentDirection = 'right';
                    gamePiece.futureDirection = 'left';
                    assert.strictEqual(gamePiece.isSetForReverse(), true);
                    gamePiece.currentDirection = 'up';
                    gamePiece.futureDirection = 'down';
                    assert.strictEqual(gamePiece.isSetForReverse(), true);
                    gamePiece.currentDirection = 'down';
                    gamePiece.futureDirection = 'up';
                    assert.strictEqual(gamePiece.isSetForReverse(), true);
                });
                it('Should return false if not set for reverse', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.currentDirection = 'right';
                    gamePiece.futureDirection = 'right';
                    assert.strictEqual(gamePiece.isSetForReverse(), false);
                    gamePiece.currentDirection = 'up';
                    gamePiece.futureDirection = 'up';
                    assert.strictEqual(gamePiece.isSetForReverse(), false);
                    gamePiece.currentDirection = 'down';
                    gamePiece.futureDirection = 'left';
                    assert.strictEqual(gamePiece.isSetForReverse(), false);
                    gamePiece.currentDirection = 'right';
                    gamePiece.futureDirection = 'up';
                    assert.strictEqual(gamePiece.isSetForReverse(), false);
                });
                it('Should return false for invalid directions', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.currentDirection = 'right';
                    gamePiece.futureDirection = 'invalidDirection';
                    assert.strictEqual(gamePiece.isSetForReverse(), false);
                    gamePiece.currentDirection = 'invalidDirection';
                    gamePiece.futureDirection = 'up';
                    assert.strictEqual(gamePiece.isSetForReverse(), false);
                });
            });
            describe('Test on the function nmsp.Piece.prototype.reverseDirection', function () {
                it('Should reverse the direction and set the current and future directions', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.currentDirection = 'left';
                    gamePiece.reverseDirection();
                    assert.strictEqual(gamePiece.currentDirection, 'right');
                    assert.strictEqual(gamePiece.futureDirection, 'right');
                    gamePiece.currentDirection = 'right';
                    gamePiece.reverseDirection();
                    assert.strictEqual(gamePiece.currentDirection, 'left');
                    assert.strictEqual(gamePiece.futureDirection, 'left');
                    gamePiece.currentDirection = 'down';
                    gamePiece.reverseDirection();
                    assert.strictEqual(gamePiece.currentDirection, 'up');
                    assert.strictEqual(gamePiece.futureDirection, 'up');
                    gamePiece.currentDirection = 'up';
                    gamePiece.reverseDirection();
                    assert.strictEqual(gamePiece.currentDirection, 'down');
                    assert.strictEqual(gamePiece.futureDirection, 'down');
                });
            });
            describe('Test on the function nmsp.Piece.prototype.isXYInBentCell', function () {
                it('Should return true for center in bent cells', function () {
                    let gamePiece = nmsp.gamePiece;
                    assert.isTrue(gamePiece.isXYInBentCell([10,10]));
                    assert.isTrue(gamePiece.isXYInBentCell([10,490]));
                    assert.isTrue(gamePiece.isXYInBentCell([490,10]));
                    assert.isTrue(gamePiece.isXYInBentCell([490,490]));
                    assert.isTrue(gamePiece.isXYInBentCell([5,5]));
                    assert.isTrue(gamePiece.isXYInBentCell([10,15]));
                    assert.isTrue(gamePiece.isXYInBentCell([12,12]));
                    assert.isTrue(gamePiece.isXYInBentCell([499,499]));
                });
                it('Should return false for center in non bent cells', function () {
                    let gamePiece = nmsp.gamePiece;
                    assert.isFalse(gamePiece.isXYInBentCell([250,250]));
                    assert.isFalse(gamePiece.isXYInBentCell([120,345]));
                    assert.isFalse(gamePiece.isXYInBentCell([10,30]));
                    assert.isFalse(gamePiece.isXYInBentCell([250,270]));
                    assert.isFalse(gamePiece.isXYInBentCell([252,274]));
                    assert.isFalse(gamePiece.isXYInBentCell([257,271]));
                    assert.isFalse(gamePiece.isXYInBentCell([266,144]));
                });
            });
            describe('Test on the function nmsp.Piece.prototype.isExactlyInBentCell', function () {
                it('Should return true for exactly in bent cells', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x = 10;gamePiece.y = 10;
                    assert.isTrue(gamePiece.isExactlyInBentCell());
                    gamePiece.x = 490;gamePiece.y = 490;
                    assert.isTrue(gamePiece.isExactlyInBentCell());
                });
                it('Should return false for not exactly in bent cells', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x = 11;gamePiece.y = 11;
                    assert.isFalse(gamePiece.isExactlyInBentCell());
                    gamePiece.x = 491;gamePiece.y = 491;
                    assert.isFalse(gamePiece.isExactlyInBentCell());
                });
            });
            describe('Test on the function nmsp.Piece.prototype.checkForCellEquality', function () {
                it('Should return true for match', function () {
                    assert.isTrue(nmsp.gamePiece.checkForCellEquality([145,157],[145,157]));
                    assert.isTrue(nmsp.gamePiece.checkForCellEquality([876,477],[876,477]));
                    assert.isTrue(nmsp.gamePiece.checkForCellEquality([-122,354],[-122,354]));
                    assert.isTrue(nmsp.gamePiece.checkForCellEquality([0,0],[0,0]));
                    assert.isTrue(nmsp.gamePiece.checkForCellEquality([-256,-157],[-256,-157]));
                });
                it('Should return false for match', function () {
                    assert.isFalse(nmsp.gamePiece.checkForCellEquality([145,157],[145,156]));
                    assert.isFalse(nmsp.gamePiece.checkForCellEquality([878,477],[876,477]));
                    assert.isFalse(nmsp.gamePiece.checkForCellEquality([-122,354],[122,350]));
                    assert.isFalse(nmsp.gamePiece.checkForCellEquality([0,0],[1,0]));
                    assert.isFalse(nmsp.gamePiece.checkForCellEquality([256,-157],[-256,-157]));
                });
            });
            describe('Test on the function nmsp.Piece.isExactlyInTerminalCell', function () {
                it('Should return true for exactly in terminal cell', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x = 30; gamePiece.y = 490;
                    assert.isTrue(gamePiece.isExactlyInTerminalCell());
                });
                it('Should return false for not exactly in terminal cell', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x = 10; gamePiece.y = 490;
                    assert.isFalse(gamePiece.isExactlyInTerminalCell());
                    gamePiece.x =11; gamePiece.y = 490;
                    assert.isFalse(gamePiece.isExactlyInTerminalCell());
                    gamePiece.x =10; gamePiece.y = 489;
                    assert.isFalse(gamePiece.isExactlyInTerminalCell());
                    gamePiece.x =250; gamePiece.y = 250;
                    assert.isFalse(gamePiece.isExactlyInTerminalCell());
                });
            });
            describe('Test on the function nmsp.Piece.isExactlyInJunctionCell', function () {
                it('Should return true for exactly in junction cell', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x = 250; gamePiece.y = 250;
                    assert.isTrue(gamePiece.isExactlyInJunctionCell());
                    gamePiece.x = 10; gamePiece.y = 10;
                    assert.isTrue(gamePiece.isExactlyInJunctionCell());
                });
                it('Should return false for not exactly in junction cell', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x = 255; gamePiece.y = 255;
                    assert.isFalse(gamePiece.isExactlyInJunctionCell());
                    gamePiece.x = 251; gamePiece.y = 250;
                    assert.isFalse(gamePiece.isExactlyInJunctionCell());
                    gamePiece.x = 250; gamePiece.y = 251;
                    assert.isFalse(gamePiece.isExactlyInJunctionCell());
                    gamePiece.x = 30; gamePiece.y = 490;
                    assert.isFalse(gamePiece.isExactlyInJunctionCell());
                });
            });
            describe('Test on the function nmsp.Piece.isCurrentDirectionPossible', function () {
                it('Should return true for possible directions', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x = 250; gamePiece.y = 250;
                    gamePiece.currentDirection = 'left';
                    assert.isTrue(gamePiece.isCurrentDirectionPossible());
                    gamePiece.currentDirection = 'right';
                    assert.isTrue(gamePiece.isCurrentDirectionPossible());
                    gamePiece.currentDirection = 'up';
                    assert.isTrue(gamePiece.isCurrentDirectionPossible());
                    gamePiece.currentDirection = 'down';
                    assert.isTrue(gamePiece.isCurrentDirectionPossible());
                    gamePiece.x = 20; gamePiece.y = 490;
                    gamePiece.currentDirection = 'right';
                    assert.isTrue(gamePiece.isCurrentDirectionPossible());
                    gamePiece.currentDirection = 'left';
                    assert.isTrue(gamePiece.isCurrentDirectionPossible());
                });
                it('Should return false for impossible directions', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x = 30; gamePiece.y = 490;
                    gamePiece.currentDirection = 'right';
                    assert.isFalse(gamePiece.isCurrentDirectionPossible());
                    gamePiece.currentDirection = 'up';
                    assert.isFalse(gamePiece.isCurrentDirectionPossible());
                    gamePiece.currentDirection = 'down';
                    assert.isFalse(gamePiece.isCurrentDirectionPossible());
                    gamePiece.x = 20; gamePiece.y = 490;
                    gamePiece.currentDirection = 'up';
                    assert.isFalse(gamePiece.isCurrentDirectionPossible());
                    gamePiece.currentDirection = 'down';
                    assert.isFalse(gamePiece.isCurrentDirectionPossible());

                });
            });
            describe('Test on the function nmsp.Piece.isFutureDirectionPossible', function () {
                it('Should return true for possible directions', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x = 250; gamePiece.y = 250;
                    gamePiece.futureDirection = 'left';
                    assert.isTrue(gamePiece.isFutureDirectionPossible());
                    gamePiece.futureDirection = 'right';
                    assert.isTrue(gamePiece.isFutureDirectionPossible());
                    gamePiece.futureDirection = 'up';
                    assert.isTrue(gamePiece.isFutureDirectionPossible());
                    gamePiece.futureDirection = 'down';
                    assert.isTrue(gamePiece.isFutureDirectionPossible());
                    gamePiece.x = 20; gamePiece.y = 490;
                    gamePiece.futureDirection = 'right';
                    assert.isTrue(gamePiece.isFutureDirectionPossible());
                    gamePiece.futureDirection = 'left';
                    assert.isTrue(gamePiece.isFutureDirectionPossible());
                });
                it('Should return false for impossible directions', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x = 30; gamePiece.y = 490;
                    gamePiece.futureDirection = 'right';
                    assert.isFalse(gamePiece.isFutureDirectionPossible());
                    gamePiece.futureDirection = 'up';
                    assert.isFalse(gamePiece.isFutureDirectionPossible());
                    gamePiece.futureDirection = 'down';
                    assert.isFalse(gamePiece.isFutureDirectionPossible());
                    gamePiece.x = 20; gamePiece.y = 490;
                    gamePiece.futureDirection = 'up';
                    assert.isFalse(gamePiece.isFutureDirectionPossible());
                    gamePiece.futureDirection = 'down';
                    assert.isFalse(gamePiece.isFutureDirectionPossible());
                });
            });
            describe('Test on the function nmsp.Piece.getObviousDirection', function () {
                it('Should return the correct direction when in bent cell', function () {
                    let gamePiece = nmsp.gamePiece;
                    gamePiece.x = 10; gamePiece.y = 490; gamePiece.currentDirection = 'left';
                    assert.strictEqual(gamePiece.getObviousDirection(),'up');
                    gamePiece.currentDirection = 'down';
                    assert.strictEqual(gamePiece.getObviousDirection(),'right');
                    gamePiece.x = 490; gamePiece.y = 490; gamePiece.currentDirection = 'right';
                    assert.strictEqual(gamePiece.getObviousDirection(),'up');
                    gamePiece.currentDirection = 'down';
                    assert.strictEqual(gamePiece.getObviousDirection(),'left');
                    gamePiece.x = 490; gamePiece.y = 10; gamePiece.currentDirection = 'right';
                    assert.strictEqual(gamePiece.getObviousDirection(),'down');
                    gamePiece.currentDirection = 'up';
                    assert.strictEqual(gamePiece.getObviousDirection(),'left');
                    gamePiece.x = 10; gamePiece.y = 10; gamePiece.currentDirection = 'left';
                    assert.strictEqual(gamePiece.getObviousDirection(),'down');
                    gamePiece.currentDirection = 'up';
                    assert.strictEqual(gamePiece.getObviousDirection(),'right');
                });
            });
            describe('Test on the function nmsp.Piece.setMovementDirection', function () {
                it('Should set the future direction', function () {
                    let gamePiece = nmsp.gamePiece;
                    nmsp.playStarted = true;
                    gamePiece.setMovementDirection('left');
                    assert.strictEqual(gamePiece.futureDirection, 'left');
                    gamePiece.setMovementDirection('right');
                    assert.strictEqual(gamePiece.futureDirection, 'right');
                    gamePiece.setMovementDirection('up');
                    assert.strictEqual(gamePiece.futureDirection, 'up');
                    gamePiece.setMovementDirection('down');
                    assert.strictEqual(gamePiece.futureDirection, 'down');
                });
                it('Should alter the piece movement timer', function () {
                    let clearSpy = sinon.spy(window, 'clearInterval');
                    let setTimerSpy = sinon.spy(window, 'setInterval');
                    nmsp.playStarted = true;
                    nmsp.gamePiece.setMovementDirection('up');
                    assert.strictEqual(clearSpy.callCount,1);
                    assert.strictEqual(setTimerSpy.callCount,1);
                    clearSpy.restore();
                    setTimerSpy.restore();
                });
                it('Should tick the timers', function () {
                    let spy = sinon.spy(nmsp.gamePiece, 'move');
                    nmsp.playStarted = false;
                    nmsp.gamePiece.setMovementDirection('left');
                    clock.tick(100);
                    assert.isTrue(spy.callCount > 0);
                    spy.restore();
                });
                it('Should start play if needed', function () {
                    let startPlaySpy = sinon.spy(nmsp,'startPlay');
                    nmsp.playStarted = true;
                    nmsp.gamePiece.setMovementDirection('right');
                    assert.strictEqual(startPlaySpy.callCount,0);
                    nmsp.playStarted = false;
                    startPlaySpy.restore;
                    nmsp.gamePiece.setMovementDirection('left');
                    assert.strictEqual(startPlaySpy.callCount,1);
                    nmsp.playStarted = true;
                    startPlaySpy.restore();
                });
            });
            describe('Test on the function nmsp.opponentPiece.updatePathArray', function () {
                it('Should compute shortest path and update path array accordingly', function () {
                    let pathArrayBck = nmsp.pathArray;
                    for(let i = 0;i< nmsp.opponentPiece.length;i++) {
                        let opponent = nmsp.opponentPiece[i];
                        let pathArray = nmsp.util.initialisePathArray();
                        opponent.updatePathArray(nmsp.util.getIJFromPiece(nmsp.gamePiece), pathArray, 0);
                        let rowUpdatedCount = 0;
                        let columnUpdatedCount = 0;
                        for(let pi=0;pi<nmsp.config.canvasDivY;pi++)
                            for(let pj=0;pj<nmsp.config.canvasDivX;pj++) {
                                let breakLoop = false;
                                if(pathArray[pi][pj]<nmsp.config.highValue) {
                                    rowUpdatedCount++;
                                    break;
                                }
                            }
                        for(let pi=0;pi<nmsp.config.canvasDivY;pi++)
                            for(let pj=0;pj<nmsp.config.canvasDivX;pj++) {
                                let breakLoop = false;
                                if(pathArray[pj][pi]<nmsp.config.highValue) {
                                    columnUpdatedCount++;
                                    break;
                                }
                            }
                        assert.strictEqual(rowUpdatedCount,25);
                        assert.strictEqual(columnUpdatedCount,25);
                    }
                });
            });
            describe('Test on the function nmsp.opponentPiece.getBestDirectionFromPathArray', function () {
                it('Should calculate best direction based on the path array', function () {
                    let opponent = nmsp.opponentPiece[0];
                    let hv = nmsp.config.highValue;
                    let pathArray = [[hv,hv,hv,hv],
                        [hv,4,hv,hv],
                        [5,hv,7,hv],
                        [hv,10,hv,hv]];
                    opponent.x = 30;opponent.y = 50;
                    nmsp.config.canvasDivX = 4;nmsp.config.canvasDivY = 4;
                    nmsp.config.canvasHeight = 80;nmsp.config.canvasWidth = 80;
                    let direction = opponent.getBestDirectionFromPathArray(pathArray);
                    assert.strictEqual(direction,'up');
                    pathArray = [[hv,hv,hv,hv],
                        [hv,4,hv,hv],
                        [5,hv,3,hv],
                        [hv,2,hv,hv]];
                    direction = opponent.getBestDirectionFromPathArray(pathArray);
                    assert.strictEqual(direction,'down');
                    pathArray = [[hv,hv,hv,hv],
                        [hv,4,hv,hv],
                        [hv,hv,hv,hv],
                        [hv,hv,hv,hv]];
                    direction = opponent.getBestDirectionFromPathArray(pathArray);
                    assert.strictEqual(direction,'up');
                    pathArray = [[hv,hv,hv,hv],
                        [hv,8,hv,hv],
                        [4,hv,4,hv],
                        [hv,78,hv,hv]];
                    direction = opponent.getBestDirectionFromPathArray(pathArray);
                    assert.oneOf(direction, ['left','right']);
                    pathArray = [[hv,hv,hv,hv],
                        [hv,4,hv,hv],
                        [5,hv,2,hv],
                        [hv,2,hv,hv]];
                    direction = opponent.getBestDirectionFromPathArray(pathArray);
                    assert.oneOf(direction,['down','right']);
                    nmsp.config.canvasDivX = 25;nmsp.config.canvasDivY = 25;
                    nmsp.config.canvasHeight = 500;nmsp.config.canvasWidth = 500;
                });
            });
            describe('Test on the function nmsp.opponentPiece.computeBestDirection', function () {
                it('Should return a valid direction', function () {
                    let opponent = nmsp.opponentPiece[0];
                    let bestDirection = null;
                    for(let i=0;i<100;i++) {
                        setTimeout(function () {
                            bestDirection = opponent.computeBestDirection();
                        }, 100);
                        setTimeout(function () {
                            if (bestDirection !== null)
                                assertDirection(bestDirection, ['up', 'down', 'right', 'left']);
                            bestDirection = null;
                        },120);
                    }
                });
            });
            describe('Test on the function nmsp.bindOpponentPieceFunctions', function () {
                it('Should bind all the necessary functions to oppoent piece', function () {
                    let mockOpponent = nmsp.createOpponentPiece(100,100);
                    nmsp.bindOpponentPieceFunctions(mockOpponent);
                    assert.isNotNull(mockOpponent.move);
                    assert.isNotNull(mockOpponent.computeBestDirection);
                    assert.isNotNull(mockOpponent.getBestDirectionFromPathArray);
                    assert.isNotNull(mockOpponent.updatePathArray);
                });
            });
            describe('Test on the function nmsp.createOpponentPiece', function () {
                it('Should return a valid opponentPiece', function () {
                    let opponent = nmsp.createOpponentPiece(55,55);
                    assert.strictEqual(opponent.x, 55);
                    assert.strictEqual(opponent.y, 55);
                    assert.strictEqual(opponent.colour, nmsp.config.opponentPieceColour);
                    assert.strictEqual(opponent.radius, nmsp.config.opponentPieceRadius);
                    assert.strictEqual(opponent.minDistanceToGamePiece, nmsp.config.highValue);
                });
                it('Should draw something on the canvas', function() {
                    let stubStroke = sinon.stub(nmsp.canvas.getContext('2d'),'stroke');
                    let stubFill = sinon.stub(nmsp.canvas.getContext('2d'),'fill');
                    nmsp.createOpponentPiece(55,55);
                    sinon.assert.callCount(stubStroke,1);
                    sinon.assert.callCount(stubFill,1);
                    stubStroke.restore();
                    stubFill.restore();

                });
                it('Should bind functions to opponent piece', function () {
                    let stubBind = sinon.stub(nmsp,'bindOpponentPieceFunctions');
                    nmsp.createOpponentPiece(55,55);
                    sinon.assert.callCount(stubBind,1);
                    stubBind.restore();
                });
            });
            describe('Test on the function nmsp.addOpponent', function () {
                it('Should create the opponent piece',function () {
                    let stub = sinon.stub(nmsp, 'createOpponentPiece');
                    stub.returns(nmsp.opponentPiece[0]);
                    nmsp.addOpponent(55,55);
                    sinon.assert.callCount(stub, 1);
                    stub.restore();
                });
                it('Should draw something on the canvas', function () {
                    let opponent  = nmsp.opponentPiece[0];
                    let stub = sinon.stub(nmsp, 'createOpponentPiece');
                    stub.returns(nmsp.opponentPiece[0]);
                    let stubStroke = sinon.stub(opponent.ctx,'stroke');
                    let stubFill = sinon.stub(opponent.ctx,'fill');
                    nmsp.addOpponent(55,55);
                    sinon.assert.callCount(stubStroke,1);
                    sinon.assert.callCount(stubFill,1);
                    stubStroke.restore();
                    stubFill.restore();
                    stub.restore();
                });
                it('Should add the new opponent to the opponent array', function () {
                    let stub = sinon.stub(nmsp.opponentPiece, "push");
                    nmsp.addOpponent(65,65);
                    sinon.assert.callCount(stub,1);
                });
            });
            describe('Test on the function nmsp.util.createRestrictedZone', function () {
                it('Should draw something on the canvas', function () {
                    let stubFillRect = sinon.stub(nmsp.gamePiece.ctx,'fillRect');
                    let stubFill = sinon.stub(nmsp.gamePiece.ctx,'fill');
                    nmsp.util.createRestrictedZone();
                    sinon.assert.callCount(stubFillRect,1);
                    sinon.assert.callCount(stubFill,1);
                    stubFillRect.restore();
                    stubFill.restore();
                });
            });
            describe('Test on the function nmsp.util.isInsideCanvasArea', function () {
                it('Should return true when inside canvas area', function () {
                    assert.strictEqual(true, nmsp.util.isInsideCanvasArea(10,10));
                    assert.strictEqual(true, nmsp.util.isInsideCanvasArea(0,0));
                    assert.strictEqual(true, nmsp.util.isInsideCanvasArea(102,433));
                    assert.strictEqual(true, nmsp.util.isInsideCanvasArea(499,266));
                    assert.strictEqual(true, nmsp.util.isInsideCanvasArea(500,500));
                });
                it('Should return false when not inside canvas area', function () {
                    assert.strictEqual(nmsp.util.isInsideCanvasArea(-1,-100), false);
                    assert.strictEqual(nmsp.util.isInsideCanvasArea(501,501), false);
                    assert.strictEqual(nmsp.util.isInsideCanvasArea(120,588), false);
                    assert.strictEqual(nmsp.util.isInsideCanvasArea(-12,411), false);
                    assert.strictEqual(nmsp.util.isInsideCanvasArea(-155,655), false);
                });
            });
            describe('Test on the function nmsp.util.isInsideRestrictedArea', function () {
                it('Should return true when inside restricted area', function () {
                    assert.strictEqual(nmsp.util.isInsideRestrictedArea([30,30]),true);
                    assert.strictEqual(nmsp.util.isInsideRestrictedArea([25,25]),true);
                    assert.strictEqual(nmsp.util.isInsideRestrictedArea([-1,-1]),true);
                    assert.strictEqual(nmsp.util.isInsideRestrictedArea([-1,10]),true);
                    assert.strictEqual(nmsp.util.isInsideRestrictedArea([10,-1]),true);
                    assert.strictEqual(nmsp.util.isInsideRestrictedArea([577,788]),true);
                    assert.strictEqual(nmsp.util.isInsideRestrictedArea([245,577]),true);
                });
                it('Should return false when outside restricted area', function () {
                    assert.strictEqual(nmsp.util.isInsideRestrictedArea([10,10]),false);
                    assert.strictEqual(nmsp.util.isInsideRestrictedArea([1,1]),false);
                    assert.strictEqual(nmsp.util.isInsideRestrictedArea([25,10]),false);
                    assert.strictEqual(nmsp.util.isInsideRestrictedArea([10,25]),false);
                    assert.strictEqual(nmsp.util.isInsideRestrictedArea([8,9]),false);
                });
            });
            describe('Test on the function nmsp.util.getIJFromPiece', function () {
                it('Should return the IJ cordinates from piece', function () {
                    let piece = nmsp.gamePiece;
                    let result = null;
                    piece.x = 10;piece.y = 10;
                    result = nmsp.util.getIJFromPiece(piece);
                    assert.equal(result[0],0);
                    assert.equal(result[1],0);
                    piece.x = 25;piece.y = 25;
                    result = nmsp.util.getIJFromPiece(piece);
                    assert.equal(result[0],1);
                    assert.equal(result[1],1);
                    piece.x = 499;piece.y = 499;
                    result = nmsp.util.getIJFromPiece(piece);
                    assert.equal(result[0],24);
                    assert.equal(result[1],24);
                    piece.x = 29;piece.y = 10;
                    result = nmsp.util.getIJFromPiece(piece);
                    assert.equal(result[0],0);
                    assert.equal(result[1],1);
                });
            });
            describe('Test on the function nmsp.util.getIJFromXY', function () {
                it('Should calculate the IJ cordinates from XY', function () {
                    let value = nmsp.util.getIJFromXY([10,10]);
                    assert.strictEqual(value[0],0);
                    assert.strictEqual(value[1],0);
                    value = nmsp.util.getIJFromXY([25,25]);
                    assert.strictEqual(value[0],1);
                    assert.strictEqual(value[1],1);
                    value = nmsp.util.getIJFromXY([499,499]);
                    assert.strictEqual(value[0],24);
                    assert.strictEqual(value[1],24);
                    value = nmsp.util.getIJFromXY([10,25]);
                    assert.strictEqual(value[0],1);
                    assert.strictEqual(value[1],0);
                });
            });
            describe('Test on the function nmsp.util.getIJFromXY', function () {
                it('Should calculate the XY cordinates from IJ', function () {
                    let value = nmsp.util.getXYFromIJ([0,0]);
                    assert.strictEqual(value[0],10);
                    assert.strictEqual(value[1],10);
                    value = nmsp.util.getXYFromIJ([1,1]);
                    assert.strictEqual(value[0],30);
                    assert.strictEqual(value[1],30);
                    value = nmsp.util.getXYFromIJ([24,24]);
                    assert.strictEqual(value[0],490);
                    assert.strictEqual(value[1],490);
                    value = nmsp.util.getXYFromIJ([0,1]);
                    assert.strictEqual(value[0],30);
                    assert.strictEqual(value[1],10);
                });
            });
            describe('Test on the function nmsp.util.isOpponentPresentInIJ', function () {
                it('Should return true if opponent is present', function () {
                    assert.strictEqual(true, nmsp.util.isOpponentPresentInIJ([0,0]));
                    assert.strictEqual(true, nmsp.util.isOpponentPresentInIJ([0,24]));
                    assert.strictEqual(true, nmsp.util.isOpponentPresentInIJ([24,0]));
                });
                it('Should return false if opponent is not present', function () {
                    assert.strictEqual(false, nmsp.util.isOpponentPresentInIJ([1,1]));
                    assert.strictEqual(false, nmsp.util.isOpponentPresentInIJ([20,12]));
                    assert.strictEqual(false, nmsp.util.isOpponentPresentInIJ([58,55]));
                    assert.strictEqual(false, nmsp.util.isOpponentPresentInIJ([14,10]));
                    assert.strictEqual(false, nmsp.util.isOpponentPresentInIJ([24,23]));
                    assert.strictEqual(false, nmsp.util.isOpponentPresentInIJ([78,65]));
                });
            });
            describe('Test on the function nmsp.util.getReverseDirection', function () {
                it('Should return the reverse direction', function () {
                    assert.strictEqual('right',nmsp.util.getReverseDirection('left'));
                    assert.strictEqual('left',nmsp.util.getReverseDirection('right'));
                    assert.strictEqual('up',nmsp.util.getReverseDirection('down'));
                    assert.strictEqual('down',nmsp.util.getReverseDirection('up'));
                });
                it('Should return null for invalid inputs', function () {
                    assert.strictEqual(null,nmsp.util.getReverseDirection('left1'));
                    assert.strictEqual(null,nmsp.util.getReverseDirection('Left'));
                    assert.strictEqual(null,nmsp.util.getReverseDirection(0));
                    assert.strictEqual(null,nmsp.util.getReverseDirection(true));
                });
            });
            describe('Test on the function nmsp.util.initialisePathArray', function () {
                let pathArray = nmsp.util.initialisePathArray();
                it('Should initialise the path array',function () {
                    assert.isNotNull(pathArray);
                    for(let i = 0;i<nmsp.config.canvasDivX;i++)
                        for (let j = 0;j<nmsp.config.canvasDivY;j++)
                            assert.strictEqual(nmsp.config.highValue, pathArray[i][j]);
                });
            });
            describe('Test on the function nmsp.util.isXYInTerminalCell', function () {
                it('Should return true if in terminal cell', function () {
                    assert.strictEqual(true,nmsp.util.isXYInTerminalCell([30,490]));
                    assert.strictEqual(true,nmsp.util.isXYInTerminalCell([35,485]));
                    assert.strictEqual(true,nmsp.util.isXYInTerminalCell([25,495]));
                });
                it('Should return false if not in terminal cell', function () {
                    assert.strictEqual(false,nmsp.util.isXYInTerminalCell([10,490]));
                    assert.strictEqual(false,nmsp.util.isXYInTerminalCell([50,50]));
                    assert.strictEqual(false,nmsp.util.isXYInTerminalCell([130,270]));
                    assert.strictEqual(false,nmsp.util.isXYInTerminalCell([130,290]));
                    assert.strictEqual(false,nmsp.util.isXYInTerminalCell([250,250]));
                });
            });
            describe('Test on the function nmsp.util.isXYInJunctionCell', function () {
                it('Should return true if in junction cell', function () {
                    assert.strictEqual(true, nmsp.util.isXYInJunctionCell([250,250]));
                    assert.strictEqual(true, nmsp.util.isXYInJunctionCell([255,255]));
                    assert.strictEqual(true, nmsp.util.isXYInJunctionCell([245,255]));
                    assert.strictEqual(true, nmsp.util.isXYInJunctionCell([255,245]));
                    assert.strictEqual(true, nmsp.util.isXYInJunctionCell([10,10]));
                    assert.strictEqual(true, nmsp.util.isXYInJunctionCell([15,15]));
                    assert.strictEqual(true, nmsp.util.isXYInJunctionCell([5,15]));
                    assert.strictEqual(true, nmsp.util.isXYInJunctionCell([15,5]));
                });
                it('Should return false if not in junction cell', function () {
                    assert.strictEqual(false, nmsp.util.isXYInJunctionCell([50,90]));
                    assert.strictEqual(false, nmsp.util.isXYInJunctionCell([70,130]));
                    assert.strictEqual(false, nmsp.util.isXYInJunctionCell([290,130]));
                    assert.strictEqual(false, nmsp.util.isXYInJunctionCell([210,110]));
                    assert.strictEqual(false, nmsp.util.isXYInJunctionCell([70,210]));
                });
            });
            describe('Test on the function nmsp.util.levelUp', function () {
                it('Should clear gamepiece and opponent intervals', function () {
                    let clearStub = sinon.stub(window,'clearInterval');
                    let timerStub = sinon.stub(nmsp.timer,'push');
                    nmsp.level = 1;
                    nmsp.util.levelUp();
                    assert.strictEqual(clearStub.callCount,2);
                    assert.strictEqual(timerStub.callCount,2);
                    clearStub.restore();
                    timerStub.restore();
                });
            });
            describe('Test on the function nmsp.util.checkForCatch', function () {
                it('Should not call end play when a catch hasnt happened', function () {
                    let endStub = sinon.stub(nmsp,'endPlay');
                    nmsp.util.checkForCatch();
                    assert.strictEqual(endStub.callCount,0);
                    endStub.restore();
                });
                it('Should call end play when a catch has happened', function () {
                    let endStub = sinon.stub(nmsp,'endPlay');
                    nmsp.gamePiece.x = 10;nmsp.gamePiece.y = 10;
                    nmsp.util.checkForCatch();
                    nmsp.gamePiece.x = 15;nmsp.gamePiece.y = 15;
                    nmsp.util.checkForCatch();
                    nmsp.gamePiece.x = 19;nmsp.gamePiece.y = 19;
                    nmsp.util.checkForCatch();
                    assert.strictEqual(endStub.callCount,3);
                    nmsp.gamePiece.x = 250;nmsp.gamePiece.y = 250;
                    endStub.restore();
                });
            });
            describe('Test on the function nmsp.startScoring', function () {
                it('Should return true', function () {
                    assert.strictEqual(nmsp.startScoring(),true);
                });
                it('Should push the timer', function () {
                    let stub = sinon.stub(nmsp.timer,'push');
                    nmsp.startScoring();
                    assert.strictEqual(stub.callCount,1);
                });
                it('Should tick the timer', function () {
                    let spy = sinon.spy(nmsp,'checkForLevelUp');
                    nmsp.startScoring();
                    clock.tick(nmsp.config.scoreChangeInterval+10);
                    assert.strictEqual(spy.callCount, 1);
                    spy.restore();
                });
            });
            describe('Test on the function nmsp.checkForLevelUp', function () {
                it('Should increment the level by 1 if needed', function () {
                    nmsp.score = 500;nmsp.config.levelChangeScore = 100;
                    nmsp.level = 1;
                    nmsp.checkForLevelUp();
                    assert.strictEqual(nmsp.level,2);
                    nmsp.level = 10;
                    nmsp.checkForLevelUp();
                    assert.strictEqual(nmsp.level,11);
                });
                it('Should invoke levelUp if needed', function () {
                    let stub = sinon.stub(nmsp.util,'levelUp');
                    nmsp.score = 500;nmsp.config.levelChangeScore = 100;
                    nmsp.checkForLevelUp();
                    assert.strictEqual(stub.callCount,1);
                    stub.restore();
                });
                it('Should not increment the level by 1 if not needed', function () {
                    nmsp.score = 500;nmsp.config.levelChangeScore = 150;
                    nmsp.level = 1;
                    nmsp.checkForLevelUp();
                    assert.strictEqual(nmsp.level,1);
                    nmsp.level = 10;
                    nmsp.checkForLevelUp();
                    assert.strictEqual(nmsp.level,10);
                });
                it('Should not invoke levelUp if not needed', function () {
                    let stub = sinon.stub(nmsp.util,'levelUp');
                    nmsp.score = 500;nmsp.config.levelChangeScore = 150;
                    nmsp.checkForLevelUp();
                    assert.strictEqual(stub.callCount,0);
                    stub.restore();
                });
            });
            describe('Test on the function nmsp.moveOpponents', function () {
                it('Should move all the opponents', function () {
                    let length = nmsp.opponentPiece.length;
                    let stub = sinon.stub(nmsp.opponentPiece[0], 'move');
                    for(let i=0;i<length;i++) {
                        nmsp.moveOpponents();
                        assert.strictEqual(stub.callCount, i+1);
                    }
                    stub.restore();
                });
            });
            describe('Test on the function nmsp.startPlay', function () {
                it('Should set playStarted to true', function () {
                    nmsp.playStarted = false;
                    nmsp.startPlay();
                    assert.strictEqual(nmsp.playStarted, true);
                });
                it('Should start the scoring', function () {
                    let spy = sinon.spy(nmsp,'startScoring');
                    nmsp.startPlay();
                    assert.strictEqual(spy.callCount,1);
                    spy.restore();
                });
                it('Should call levelUp Once',function () {
                    let spy = sinon.spy(nmsp.util, 'levelUp');
                    nmsp.startPlay();
                    assert.strictEqual(spy.callCount,1);
                    spy.restore();
                });
                it('Should tick the timer', function () {
                    let spy = sinon.spy(nmsp.util,'checkForCatch');
                    nmsp.startPlay();
                    clock.tick(nmsp.config.catchCheckInterval);
                    assert.strictEqual(spy.callCount, 1);
                    spy.restore();
                });
            });
            describe('Test on the function nmsp.endPlay', function () {
                it('Should unbind the key configurations',function () {
                    let spy = sinon.spy(nmsp.util,'unBindKeys');
                    nmsp.endPlay();
                    assert.strictEqual(spy.callCount,1);
                    spy.restore();
                });
            });
            describe('Test on the function nmsp.util.navigationKeyConfig', function () {
                it('Should set the correct movement direction for valid inputs', function () {
                    let spy = sinon.stub(nmsp.gamePiece,'setMovementDirection');
                    nmsp.util.navigationKeyConfig({keyCode:37});
                    assert.strictEqual(spy.callCount,1);
                    let arg = spy.getCalls()[0].args;
                    assert.strictEqual(arg[0],'left');
                    spy.restore();
                    spy = sinon.stub(nmsp.gamePiece,'setMovementDirection');
                    nmsp.util.navigationKeyConfig({keyCode:38});
                    assert.strictEqual(spy.callCount,1);
                    arg = spy.getCalls()[0].args;
                    assert.strictEqual(arg[0],'up');
                    spy.restore();
                    spy = sinon.stub(nmsp.gamePiece,'setMovementDirection');
                    nmsp.util.navigationKeyConfig({keyCode:39});
                    assert.strictEqual(spy.callCount,1);
                    arg = spy.getCalls()[0].args;
                    assert.strictEqual(arg[0],'right');
                    spy.restore();
                    spy = sinon.stub(nmsp.gamePiece,'setMovementDirection');
                    nmsp.util.navigationKeyConfig({keyCode:40});
                    assert.strictEqual(spy.callCount,1);
                    arg = spy.getCalls()[0].args;
                    assert.strictEqual(arg[0],'down');
                    spy.restore();
                });
                it('Should prevent default for invalid inputs', function () {
                    let event = {keyCode : 41, preventDefault:() => {this}};
                    let spy = sinon.stub(event,'preventDefault');
                    nmsp.util.navigationKeyConfig(event);
                    assert.strictEqual(spy.callCount,1);
                    event = {keyCode : 1001, preventDefault:() => {this}};
                    spy = sinon.stub(event,'preventDefault');
                    nmsp.util.navigationKeyConfig(event);
                    assert.strictEqual(spy.callCount,1);
                    event = {keyCode : -1, preventDefault:() => {this}};
                    spy = sinon.stub(event,'preventDefault');
                    nmsp.util.navigationKeyConfig(event);
                    assert.strictEqual(spy.callCount,1);
                    spy.restore();
                });
            });
            describe('Test on the function nmsp.util.unBindKeys', function () {
                it('Should remove event listner', function () {
                    let spy = sinon.spy(document, 'removeEventListener');
                    nmsp.util.unBindKeys();
                    assert.strictEqual(spy.callCount,1);
                    spy.restore();
                });
            });
            describe('Test on the function nmsp.init',function () {
                it('Should set the config object',function () {
                    let configBck = nmsp.config;
                    nmsp.config = null;
                    nmsp.init(configBck);
                    assert.isNotNull(nmsp.config);
                });
                it('Should set the canvas object', function () {
                    nmsp.canvas = null;
                    nmsp.init(nmsp.config);
                    assert.isNotNull(nmsp.canvas);
                    assert.strictEqual(nmsp.canvas.width,nmsp.config.canvasWidth);
                    assert.strictEqual(nmsp.canvas.height,nmsp.config.canvasHeight);
                });
                it('Should set the gamePiece object', function () {
                    nmsp.gamePiece = null;
                    nmsp.init(nmsp.config);
                    assert.isNotNull(nmsp.gamePiece);
                    assert.strictEqual(nmsp.gamePiece.x,nmsp.config.gamePieceStartPosition[0]);
                    assert.strictEqual(nmsp.gamePiece.y,nmsp.config.gamePieceStartPosition[1]);
                    assert.strictEqual(nmsp.gamePiece.radius,nmsp.config.gamePieceRadius);
                    assert.strictEqual(nmsp.gamePiece.colour,nmsp.config.gamePieceColour);
                });
                it('Should add the opponents', function () {
                    let opponentBck = nmsp.opponentPiece;
                    nmsp.opponentPiece = [];
                    nmsp.init(nmsp.config);
                    assert.isNotNull(nmsp.opponentPiece);
                    assert(nmsp.opponentPiece.length > 0);
                    nmsp.opponentPiece = opponentBck;
                });
                describe('Test on self invoked functions inside init', function () {
                    describe('Test on the function this.canvas.drawCanvas', function () {
                        it('Should draw the canvas', function () {
                            let spy = sinon.spy(nmsp.util,'createRestrictedZone');
                            nmsp.init(nmsp.config);
                            assert(spy.callCount > 0);
                            spy.restore();
                        });
                    });
                    describe('Test on the function this.canvas.bindKeys', function () {
                        it('Should bind keys', function () {
                            let spy = sinon.spy(document,'addEventListener');
                            nmsp.init(nmsp.config);
                            assert.strictEqual(spy.callCount,1);
                            spy.restore();
                        });
                    });
                });
                describe('Test on the normal functions inside init', function () {
                    describe('Test on the function nmsp.gamePiece.move', function () {
                        it('Should move the gamePiece', function () {
                            let spy = sinon.spy(nmsp.gamePiece,'takeOneStep');
                            nmsp.gamePiece.move();
                            assert.strictEqual(spy.callCount,1);
                            spy.restore();
                        });
                        describe('Test when gamePiece not in junction cell and is set for reverse', function () {
                            it('Should reverse direction', function () {
                                let gamePiece = nmsp.gamePiece;
                                gamePiece.currentDirection = 'left';
                                gamePiece.futureDirection = 'right';
                                gamePiece.x = 10;gamePiece.y = 30;
                                gamePiece.move();
                                assert.strictEqual(gamePiece.currentDirection,'right');
                            });
                        });
                        describe('Test when gamePiece is in junction cell', function () {
                            it('Should set current direction when future direction is possible', function () {
                                let gamePiece = nmsp.gamePiece;
                                gamePiece.currentDirection = null;
                                gamePiece.futureDirection = 'right';
                                gamePiece.move();
                                assert.strictEqual(gamePiece.currentDirection,'right');
                                gamePiece.futureDirection = 'left';
                                gamePiece.move();
                                assert.strictEqual(gamePiece.currentDirection,'left');
                                gamePiece.futureDirection = 'up';
                                gamePiece.move();
                                assert.strictEqual(gamePiece.currentDirection,'up');
                                gamePiece.futureDirection = 'down';
                                gamePiece.move();
                                assert.strictEqual(gamePiece.currentDirection,'down');
                            });
                            it('Should move to the obvious direction when in bent cell(|-) and future direction is not possible', function () {
                                let gamePiece = nmsp.gamePiece;
                                gamePiece.x = 10;gamePiece.y = 10;
                                gamePiece.currentDirection = gamePiece.futureDirection = 'up';
                                gamePiece.move();
                                assert.strictEqual(gamePiece.currentDirection,'right');
                                assert.strictEqual(gamePiece.futureDirection,'right');
                            });
                            it('Should move to reverse direction when in T junction and future direction is not possible', function () {
                                let gamePiece = nmsp.gamePiece;
                                gamePiece.x = 190;gamePiece.y = 370;
                                gamePiece.currentDirection = gamePiece.futureDirection = 'up';
                                gamePiece.move();
                                assert.strictEqual(gamePiece.currentDirection, 'down');
                                assert.strictEqual(gamePiece.futureDirection, 'down');
                            });
                        });
                        describe('Test when gamePiece is in terminal cell', function () {
                            it('Should reverse direction', function () {
                                let gamePiece = nmsp.gamePiece;
                                gamePiece.x = 30;gamePiece.y = 490;
                                gamePiece.currentDirection = gamePiece.futureDirection = 'up';
                                gamePiece.move();
                                assert.strictEqual(gamePiece.currentDirection,'down');
                                assert.strictEqual(gamePiece.futureDirection,'down');
                            });
                        });
                    });
                    describe('Test on the function this.opponentPiece.move', function () {
                        it('Should move the opponent', function () {
                            let opponent = nmsp.opponentPiece[0];
                            opponent.currentDirection = opponent.futureDirection = 'up';
                            let spy = sinon.spy(nmsp.opponentPiece[0],'takeOneStep');
                            opponent.move();
                            assert.strictEqual(spy.callCount,1);
                            spy.restore();
                        });
                        describe('Test when opponent not in junction cell and is set for reverse', function () {
                            it('Should reverse direction', function () {
                                let opponent = nmsp.opponentPiece[1];
                                opponent.currentDirection = 'left';
                                opponent.futureDirection = 'right';
                                opponent.x = 10;opponent.y = 30;
                                opponent.move();
                                assert.strictEqual(opponent.currentDirection,'right');
                            });
                        });
                        describe('Test when opponent is in junction cell', function () {
                            it('Should move to best direction if possible', function () {
                                let opponent = nmsp.opponentPiece[0];
                                opponent.move();
                                assert.strictEqual(opponent.currentDirection,'up');
                                assert.strictEqual(opponent.futureDirection,'up');
                            });
                            it('Should move to the obvious direction if best direction is not an option', function () {
                                let opponent = nmsp.opponentPiece[0];
                                let stubBestDirection = sinon.stub(nmsp.opponentPiece[0],'computeBestDirection');
                                let stubObviousDirection = sinon.stub(nmsp.opponentPiece[0],'getObviousDirection');
                                stubBestDirection.returns('down');
                                stubObviousDirection.returns('left');
                                opponent.currentDirection = opponent.futureDirection = 'left';
                                opponent.move();
                                assert.strictEqual(opponent.currentDirection,'left');
                                assert.strictEqual(opponent.futureDirection,'left');
                                stubBestDirection.restore();
                                stubObviousDirection.restore();
                            });
                            it('Should reverse direction in other cases', function () {
                                let opponent = nmsp.opponentPiece[0];
                                opponent.currentDirection = opponent.futureDirection = 'up';
                                opponent.x = 10;opponent.y = 10;
                                let stubBestDirection = sinon.stub(nmsp.opponentPiece[0],'computeBestDirection');
                                let stubObviousDirection = sinon.stub(nmsp.opponentPiece[0],'getObviousDirection');
                                stubBestDirection.returns('down');
                                stubObviousDirection.returns(null);
                                opponent.move();
                                assert.strictEqual(opponent.currentDirection,'down');
                                assert.strictEqual(opponent.futureDirection,'down');
                                stubBestDirection.restore();
                                stubObviousDirection.restore();
                            });
                        });
                    });
                });
            });
        });
    });

    function assertObject(obj) {
        it('Should be an object', function () {
            assert.isObject(obj);
        });
    }

    function assertNumber(value) {
        it('Should be a number', function () {
            assert.isNumber(value);
        });
        it('Should be in positive application value range', function () {
            expect(value).to.be.greaterThan(0);
            expect(value).to.be.lessThan(nmsp.config.highValue);
        });
    }
    function assertColour(colour) {
        it('Should be a String', function () {
            assert.isString(colour);
        });
        it('Should be one of the following : "red","blue","black","green","silver"', function () {
            expect(colour).is.oneOf(["red", "blue", "black", "green", "silver"]);
        });
    }

    function assertIs1DArray(array, cols, type) {
        it('Should be an Array', function () {
            assert.isArray(array);
        });
        if(null != cols) {
            it('Should be of exact length', function () {
                expect(array.length).to.equal(cols);
            });
        }
        it('Each element in the array should be of type '+type, function () {
            for(let i=cols-1;i>=0;i--)
                assert.typeOf(array[i], type);
        });
    }

    function assertIs2DArray(array, rows, cols, type) {
        it('Should be an Array', function () {
            assert.isArray(array);
        });
        it('Should be a 2-D array', function () {
            assert.isArray(array[0]);
        });
        describe('Checks for dimentions', function () {
            it('Should have exact number of rows', function () {
                expect(array.length).to.equal(rows);
            });
            it('Should have exact number of columns', function () {
                expect(array[0].length).to.equal(cols);
            });
        });
        describe('Check for type', function () {
            it('Each element in array should be of type '+type, function () {
                for (let i = rows - 1; i >= 0; i--)
                    for (let j = cols - 1; j >= 0; j--)
                        assert.typeOf(array[i][j], type);
            });
        });
    }

    function assertInsidePlayArea(position) {
        it('Should be inside Play Area', function () {
            expect(position[0]).to.be.lessThan(nmsp.config.canvasWidth);
            expect(position[1]).to.be.lessThan(nmsp.config.canvasHeight);
        });
    }

    function assertPercentage(value) {
        assertNumber(value);
        it('Should be a percentage, ie between 0 and 100', function () {
            expect(value).to.be.greaterThan(0);
            expect(value).to.be.lessThan(100);
        });
    }

    function assertDirection(direction,possibleValues) {
        assert.isString(direction);
        expect(direction).is.oneOf(possibleValues);
    }

});