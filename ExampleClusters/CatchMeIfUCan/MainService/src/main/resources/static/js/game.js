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

let cmiuc = {};
module.exports = cmiuc;

(function (nmsp) {

    "use strict";

    nmsp.config = { // These are the default configurations
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
                         ["1", "0", "1", "1", "1", "0", "1", "0", "0", "1", "0", "0", "1", "0", "0", "0", "0", "0", "1", "0", "1", "1", "1", "0", "1"],
                         ["1", "0", "0", "0", "0", "0", "1", "0", "0", "1", "0", "0", "1", "0", "1", "1", "1", "0", "1", "0", "1", "0", "1", "0", "1"],
                         ["1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1", "0", "1", "1", "1"]],
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

    nmsp.util = {};
    nmsp.gamePiece = null;
    nmsp.opponentPiece = [];
    nmsp.score = 0;
    nmsp.level = 0;
    nmsp.playStarted = false;

    nmsp.timer = [];
    nmsp.gamePieceTimer = null;
    nmsp.opponentTimer = null;


    nmsp.Piece = function (x, y, radius, colour) {
        //The main constructor function that acts as super type for both gamePiece and opponentPiece
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.colour = colour;
        return this;
    };

    nmsp.Piece.prototype.currentDirection = null;
    nmsp.Piece.prototype.futureDirection = null;

    nmsp.Piece.prototype.erase = function () {
        let x = this.x;
        let y = this.y;
        let radius = this.radius + nmsp.config.gamePieceSquareBuffer;
        let diameter = 2 * radius;
        nmsp.canvas.getContext("2d").clearRect(x - radius, y - radius, diameter, diameter);
    };

    nmsp.Piece.prototype.getRandomDirection = function () {
        let dirArray = ["right", "down", "left", "up"];
        return dirArray[Math.floor(Math.random() * (dirArray.length))];
    };

    nmsp.Piece.prototype.getPossibleRandomDirection = function () {
        let _this = this;
        let randomDirection = null;
        do {
            randomDirection = _this.futureDirection = this.getRandomDirection();
        } while (!_this.isFutureDirectionPossible());
        return randomDirection;
    };

    nmsp.Piece.prototype.movePieceTo = function (x, y) {
        let ctx = nmsp.canvas.getContext("2d");
        ctx.beginPath();
        ctx.arc(x, y, this.radius, 0, 2 * Math.PI);
        ctx.fillStyle = this.colour;
        ctx.fill();
        ctx.stroke();
        this.x = x;
        this.y = y;
        this.ctx = ctx;
    };

    nmsp.Piece.prototype.takeOneStep = function (direction) {

        if(!(direction === 'left' || direction === 'right' || direction === 'up' || direction === 'down'))
            return false;

        let xOriginal = this.x, yOriginal = this.y;
        let x = xOriginal, y = yOriginal;
        let speed = nmsp.config.gamePieceStep;
        let radius = this.radius;
        switch (direction) {
            case "left":
                x = x - speed;
                break;
            case "up":
                y = y - speed;
                break;
            case "right":
                x = x + speed;
                break;
            case "down":
                y = y + speed;
                break;
        }
        let topEdge = y - radius, bottomEdge = y + radius, leftEdge = x - radius, rightEdge = x + radius;
        let topRightCorner = [rightEdge, topEdge];
        let bottomLeftCorner = [leftEdge, bottomEdge];
        if (!nmsp.util.isInsideRestrictedArea(topRightCorner) && !nmsp.util.isInsideRestrictedArea(bottomLeftCorner)) {
            this.erase();
            this.movePieceTo(x, y);
        } else
            return false;
        return true;
    };

    nmsp.Piece.prototype.isSetForReverse = function () {
        switch (this.currentDirection) {
            case "left":
                return this.futureDirection === "right";
            case "right":
                return this.futureDirection === "left";
            case "up":
                return this.futureDirection === "down";
            case "down":
                return this.futureDirection === "up";
        }
        return false;
    };

    nmsp.Piece.prototype.reverseDirection = function () {
        if (this.currentDirection === "left")
            this.currentDirection = this.futureDirection = "right";
        else if (this.currentDirection === "right")
            this.currentDirection = this.futureDirection = "left";
        else if (this.currentDirection === "up")
            this.currentDirection = this.futureDirection = "down";
        else if (this.currentDirection === "down")
            this.currentDirection = this.futureDirection = "up";
    };

    nmsp.Piece.prototype.isXYInBentCell = function (xyPair) {
        let ij = nmsp.util.getIJFromXY(xyPair);
        let i = ij[0], j = ij[1];
        let layout = nmsp.config.canvasLayout;
        let len = layout.length - 1;
        if (i === 0 || i === len)
            return (layout[i][j - 1] === '1' && (j === len || layout[i][j + 1] === '0')) || ((j === 0 || layout[i][j - 1] === '0') && layout[i][j + 1] === '1');
        else if (j === 0 || j === len)
            return (layout[i - 1][j] === '1' && (i === len || layout[i + 1][j] === '0')) || ((i === 0 || layout[i - 1][j] === '0') && layout[i + 1][j] === '1');
        else if ((layout[i - 1][j] === '1' || layout[i + 1][j] === '1') && layout[i - 1][j] !== layout[i + 1][j]) {
            return ((j !== 0 && layout[i][j - 1] === '1') && (j !== len && layout[i][j + 1] === '0')) || ((j !== 0 && layout[i][j - 1] === '0') && (j !== len && layout[i][j + 1] === '1'));
        } else if ((layout[i][j - 1] === '1' || layout[i][j + 1] === '1') && layout[i][j - 1] !== layout[i][j + 1]) {
            return ((i !== 0 && layout[i - 1][j] === '1') && (i !== len && layout[i + 1][j] === '0')) || ((i !== 0 && layout[i - 1][j] === '0') && (i !== len && layout[i + 1][j] === '1'));
        }
        return false;
    };

    nmsp.Piece.prototype.isExactlyInBentCell = function () {
        let x = this.x, y = this.y, radius = this.radius;
        let topEdge = y - radius, bottomEdge = y + radius, leftEdge = x - radius, rightEdge = x + radius;
        let center = [x, y];
        let topRightCorner = [rightEdge, topEdge];
        let bottomLeftCorner = [leftEdge, bottomEdge];
        return (this.isXYInBentCell(center) && this.isXYInBentCell(topRightCorner) && this.isXYInBentCell(bottomLeftCorner));
    };

    nmsp.Piece.prototype.checkForCellEquality = function (ijPair1, ijPair2) {
        return ijPair1[0] === ijPair2[0] && ijPair1[1] === ijPair2[1];
    };

    nmsp.Piece.prototype.isExactlyInTerminalCell = function () {
        let x = this.x, y = this.y, radius = this.radius;
        let topEdge = y - radius, bottomEdge = y + radius, leftEdge = x - radius, rightEdge = x + radius;
        let center = [x, y];
        let topRightCorner = [rightEdge, topEdge];
        let bottomLeftCorner = [leftEdge, bottomEdge];
        if (this.checkForCellEquality(nmsp.util.getIJFromXY(bottomLeftCorner), nmsp.util.getIJFromXY(topRightCorner)))
            return (nmsp.util.isXYInTerminalCell(center) && nmsp.util.isXYInTerminalCell(topRightCorner) && nmsp.util.isXYInTerminalCell(bottomLeftCorner));
        return false;
    };

    nmsp.Piece.prototype.isExactlyInJunctionCell = function () {
        let x = this.x, y = this.y, radius = this.radius;
        let topEdge = y - radius, bottomEdge = y + radius, leftEdge = x - radius, rightEdge = x + radius;
        let center = [x, y];
        let topRightCorner = [rightEdge, topEdge];
        let bottomLeftCorner = [leftEdge, bottomEdge];
        if (this.checkForCellEquality(nmsp.util.getIJFromXY(bottomLeftCorner), nmsp.util.getIJFromXY(topRightCorner)))
            return (nmsp.util.isXYInJunctionCell(center) && nmsp.util.isXYInJunctionCell(topRightCorner) && nmsp.util.isXYInJunctionCell(bottomLeftCorner));
        return false;
    };

    nmsp.Piece.prototype.isCurrentDirectionPossible = function () {
        let xOriginal = this.x, yOriginal = this.y;
        let x = xOriginal, y = yOriginal;
        let speed = nmsp.config.gamePieceStep;
        let radius = this.radius;
        switch (this.currentDirection) {
            case "left":
                x = x - speed;
                break;
            case "up":
                y = y - speed;
                break;
            case "right":
                x = x + speed;
                break;
            case "down":
                y = y + speed;
                break;
        }
        let topEdge = y - radius, bottomEdge = y + radius, leftEdge = x - radius, rightEdge = x + radius;
        let topRightCorner = [rightEdge, topEdge];
        let bottomLeftCorner = [leftEdge, bottomEdge];
        return !(nmsp.util.isInsideRestrictedArea(topRightCorner) || nmsp.util.isInsideRestrictedArea(bottomLeftCorner));
    };

    nmsp.Piece.prototype.isFutureDirectionPossible = function () {
        let xOriginal = this.x, yOriginal = this.y;
        let x = xOriginal, y = yOriginal;
        let speed = nmsp.config.gamePieceStep;
        let radius = this.radius;
        switch (this.futureDirection) {
            case "left":
                x = x - speed;
                break;
            case "up":
                y = y - speed;
                break;
            case "right":
                x = x + speed;
                break;
            case "down":
                y = y + speed;
                break;
        }
        let topEdge = y - radius, bottomEdge = y + radius, leftEdge = x - radius, rightEdge = x + radius;
        let topRightCorner = [rightEdge, topEdge];
        let bottomLeftCorner = [leftEdge, bottomEdge];
        return !(nmsp.util.isInsideRestrictedArea(topRightCorner) || nmsp.util.isInsideRestrictedArea(bottomLeftCorner));
    };

    nmsp.Piece.prototype.getObviousDirection = function () {
        let ij = nmsp.util.getIJFromPiece(this);
        let i = ij[0], j = ij[1];
        let layout = nmsp.config.canvasLayout;
        let iLen = layout.length - 1, jLen = layout[0].length - 1;
        let direction = this.currentDirection;
        let obDirection = null;

        if ('left' === direction) {
            if ((i === 0 || layout[i - 1][j] === '1') && (i === iLen || layout[i + 1][j] === '0'))
                obDirection = 'up';
            else if ((i === iLen || layout[i + 1][j] === '1') && (i === 0 || layout[i - 1][j] === '0'))
                obDirection = 'down';
            else
                obDirection = 'right';
        } else if ('right' === direction) {
            if ((i === 0 || layout[i - 1][j] === '1') && (i === iLen || layout[i + 1][j] === '0'))
                obDirection = 'up';
            else if ((i === iLen || layout[i + 1][j] === '1') && (i === 0 || layout[i - 1][j] === '0'))
                obDirection = 'down';
            else
                obDirection = 'left';
        } else if ('up' === direction) {
            if ((j === 0 || layout[i][j - 1] === '1') && (j === jLen || layout[i][j + 1] === '0'))
                obDirection = 'left';
            else if ((j === jLen || layout[i][j + 1] === '1') && (j === 0 || layout[i][j - 1] === '0'))
                obDirection = 'right';
            else
                obDirection = 'down';
        } else if ('down' === direction) {
            if ((j === 0 || layout[i][j - 1] === '1') && (j === jLen || layout[i][j + 1] === '0'))
                obDirection = 'left';
            else if ((j === jLen || layout[i][j + 1] === '1') && (j === 0 || layout[i][j - 1] === '0'))
                obDirection = 'right';
            else
                obDirection = 'up';
        }
        return obDirection;
    };

    nmsp.Piece.prototype.setMovementDirection = function (direction) {
        this.futureDirection = direction;
        window.clearInterval(nmsp.gamePieceTimer);
        nmsp.gamePieceTimer = window.setInterval(() => {
            this.move();
        }, nmsp.config.gamePieceStepInterval - ((nmsp.level - 1) * nmsp.config.levelUpSpeedChangeRate));
        if (!nmsp.playStarted)
            nmsp.startPlay();
    };

    nmsp.opponentPiece.updatePathArray = function (ijPair, pathArray, distance, restrictedDirection) {
        let i = ijPair[0], j = ijPair[1];
        let layout = nmsp.config.canvasLayout;
        let lenX = nmsp.config.canvasDivX - 1;
        let lenY = nmsp.config.canvasDivY - 1;
        let tIJPair = nmsp.util.getIJFromPiece(this);
        let ti = tIJPair[0], tj = tIJPair[1];
        let inScope = false;
        if ((i === ti && j === tj) && this.minDistanceToGamePiece > distance) {
            this.minDistanceToGamePiece = distance;
            return;
        } else if (distance > pathArray[i][j]) {
            return;
        } else if (nmsp.util.isOpponentPresentInIJ(ijPair)) {
            return;
        } else if (!nmsp.util.isXYInJunctionCell([this.x, this.y])) {
            do {
                switch (restrictedDirection) {
                    case "up":
                        i++;
                        break;
                    case "down":
                        i--;
                        break;
                    case "left":
                        j++;
                        break;
                    case "right":
                        j--;
                        break;
                }
                if ((inScope = (i >= 0 && i <= lenY && j >= 0 && j <= lenX)))
                    pathArray[i][j] = ++distance;
            } while (inScope && !nmsp.util.isXYInJunctionCell(nmsp.util.getXYFromIJ([i, j])) && (i !== ti || j !== tj));
            if (i === ti && j === tj) {
                this.minDistanceToGamePiece = this.minDistanceToGamePiece > distance ? distance : this.minDistanceToGamePiece;
                return;
            }
            i = i === -1 ? 0 : i === lenY + 1 ? lenY : i;
            j = j === -1 ? 0 : j === lenX + 1 ? lenX : j;
        } else {
            distance++;
        }
        if ("up" !== restrictedDirection && i > 0 && layout[i - 1][j] === '1') {
            if (pathArray[i - 1][j] < distance || ((i - 1) === ti && j === tj))
                return;
            pathArray[i - 1][j] = distance < pathArray[i - 1][j] ? distance : pathArray[i - 1][j];
            this.updatePathArray([i - 1, j], pathArray, distance, "down");
        }
        if ("down" !== restrictedDirection && i < lenY && layout[i + 1][j] === '1') {
            if (pathArray[i + 1][j] < distance || (i + 1) === ti && j === tj)
                return;
            pathArray[i + 1][j] = distance < pathArray[i + 1][j] ? distance : pathArray[i + 1][j];
            this.updatePathArray([i + 1, j], pathArray, distance, "up");
        }
        if ("left" !== restrictedDirection && j > 0 && layout[i][j - 1] === '1') {
            if (pathArray[i][j - 1] < distance || i === ti && (j - 1) === tj)
                return;
            pathArray[i][j - 1] = distance < pathArray[i][j - 1] ? distance : pathArray[i][j - 1];
            this.updatePathArray([i, j - 1], pathArray, distance, "right");
        }
        if ("right" !== restrictedDirection && j < lenX && layout[i][j + 1] === '1') {
            if (pathArray[i][j + 1] < distance || i === ti && (j + 1) === tj)
                return;
            pathArray[i][j + 1] = distance < pathArray[i][j + 1] ? distance : pathArray[i][j + 1];
            this.updatePathArray([i, j + 1], pathArray, distance, "left");
        }
        this.minDistanceToGamePiece = nmsp.config.highValue;
    };

    nmsp.opponentPiece.getBestDirectionFromPathArray = function (pathArray) {
        let ij = nmsp.util.getIJFromPiece(this);
        let lenX = nmsp.config.canvasDivX - 1, lenY = nmsp.config.canvasDivY - 1;
        let i = ij[0], j = ij[1];
        let min = nmsp.config.highValue, direction = null;
        if (i !== 0 && min > pathArray[i - 1][j]) {
            direction = "up";
            min = pathArray[i - 1][j];
        }
        if (i !== lenY && min > pathArray[i + 1][j]) {
            direction = "down";
            min = pathArray[i + 1][j];
        }
        if (j !== 0 && min > pathArray[i][j - 1]) {
            direction = "left";
            min = pathArray[i][j - 1];
        }
        if (j !== lenX && min > pathArray[i][j + 1]) {
            direction = "right";
        }
        return direction;
    };

    nmsp.opponentPiece.computeBestDirection = function () {
        let gamePiece = nmsp.gamePiece;
        if (Math.floor(Math.random() * 100) < nmsp.config.randomMoveProbability) //Added to make the opponents more unpredictable and to avoid all opponents converging into one
            return this.getPossibleRandomDirection();
        let pathArray = nmsp.util.initialisePathArray();
        this.updatePathArray(nmsp.util.getIJFromPiece(gamePiece), pathArray, 0);
        return this.getBestDirectionFromPathArray(pathArray);
    };

    nmsp.bindOpponentPieceFunctions = function (opponentPiece) {
        opponentPiece.move = nmsp.opponentPiece.move;
        opponentPiece.computeBestDirection = nmsp.opponentPiece.computeBestDirection;
        opponentPiece.getBestDirectionFromPathArray = nmsp.opponentPiece.getBestDirectionFromPathArray;
        opponentPiece.updatePathArray = nmsp.opponentPiece.updatePathArray;
    };

    nmsp.createOpponentPiece = (x, y) => {
        let ctx = nmsp.canvas.getContext("2d");
        ctx.beginPath();
        ctx.arc(x, y, nmsp.config.opponentPieceRadius, 0, 2 * Math.PI);
        ctx.fillStyle = nmsp.config.opponentPieceColour;
        ctx.fill();
        ctx.stroke();
        let opponentPiece = new nmsp.Piece(x, y, nmsp.config.opponentPieceRadius, nmsp.config.opponentPieceColour);
        opponentPiece.ctx = ctx;
        opponentPiece.minDistanceToGamePiece = nmsp.config.highValue;
        nmsp.bindOpponentPieceFunctions(opponentPiece);
        return opponentPiece;
    };

    nmsp.addOpponent = function (x, y) {
        let opponent = nmsp.createOpponentPiece(x, y);
        let ctx = opponent.ctx;
        ctx.fill();
        ctx.stroke();
        nmsp.opponentPiece.push(opponent);
    };

    nmsp.util.createRestrictedZone = function (x, y, xUnit, yUnit) {
        let ctx = nmsp.gamePiece.ctx;
        ctx.beginPath();
        ctx.fillStyle = nmsp.config.canvasRestrictedColour;
        ctx.fillRect(x * xUnit, y * yUnit, xUnit, yUnit);
        ctx.fill();
        ctx.closePath();
    };

    nmsp.util.isInsideCanvasArea = function (x, y) {
        if (x !== null && (x < 0 || x > nmsp.config.canvasWidth))
            return false;
        else return !(y !== null && (y < 0 || y > nmsp.config.canvasHeight));
    };

    nmsp.util.isInsideRestrictedArea = function (xyPair) {
        let x = xyPair[0], y = xyPair[1];
        let i = Math.floor(y / (nmsp.config.canvasHeight / nmsp.config.canvasDivY));
        let j = Math.floor(x / (nmsp.config.canvasWidth / nmsp.config.canvasDivX));
        let layout = nmsp.config.canvasLayout;
        if (!nmsp.util.isInsideCanvasArea(x, y))
            return true;
        else if (i < 0 || j < 0 || layout[i][j] === '0')
            return true;
        return false;
    };

    nmsp.util.getIJFromPiece = function (piece) {
        let x = piece.x, y = piece.y;
        let xUnit = nmsp.config.canvasWidth / nmsp.config.canvasDivX;
        let yUnit = nmsp.config.canvasHeight / nmsp.config.canvasDivY;
        let j = Math.floor(x / xUnit), i = Math.floor(y / yUnit);
        return [i, j];
    };

    nmsp.util.getIJFromXY = function (xyPair) {
        let x = xyPair[0], y = xyPair[1];
        let xUnit = nmsp.config.canvasWidth / nmsp.config.canvasDivX;
        let yUnit = nmsp.config.canvasHeight / nmsp.config.canvasDivY;
        let j = Math.floor(x / xUnit), i = Math.floor(y / yUnit);
        return [i, j];
    };

    nmsp.util.getXYFromIJ = function (ijPair) {
        let i = ijPair[0], j = ijPair[1];
        let xUnit = nmsp.config.canvasWidth / nmsp.config.canvasDivX;
        let yUnit = nmsp.config.canvasHeight / nmsp.config.canvasDivY;
        let x = Math.floor(j * xUnit) + (xUnit / 2), y = Math.floor(i * yUnit) + (yUnit / 2);
        return [x, y];
    };

    nmsp.util.isOpponentPresentInIJ = function (ijPair) {
        let i = ijPair[0], j = ijPair[1];
        let opponentCount = nmsp.config.opponentPieceStart.length;
        for (let c = opponentCount - 1; c >= 0; c--) {
            let opponent = nmsp.opponentPiece[c];
            let ij = nmsp.util.getIJFromPiece(opponent);
            if (i === ij[0] && j === ij[1])
                return true;
        }
        return false;
    };

    nmsp.util.getReverseDirection = function (direction) {
        return direction === "left" ? "right" : (direction === "right" ? "left" : direction === "down" ? "up" : direction === "up" ? "down" : null);
    };

    nmsp.util.initialisePathArray = function () {
        let pathArray = [];
        let value = nmsp.config.highValue;
        for (let i = nmsp.config.canvasDivY - 1; i >= 0; i--) {
            let row = [];
            for (let j = nmsp.config.canvasDivX - 1; j >= 0; j--)
                row.push(value);
            pathArray[i] = row;
        }
        return pathArray;
    };

    nmsp.util.isXYInTerminalCell = function (xyPair) {
        let ij = nmsp.util.getIJFromXY(xyPair);
        let i = ij[0], j = ij[1];
        let layout = nmsp.config.canvasLayout;
        let lenX = nmsp.config.canvasDivX - 1, lenY = nmsp.config.canvasDivY - 1;
        return (i === 0 || layout[i - 1][j] === '1') && (i === lenY || layout[i + 1][j] === '0') && (j === 0 || layout[i][j - 1] === '0') && (j === lenX || layout[i][j + 1] === '0') ? true :
            (i === lenY || layout[i + 1][j] === '1') && (i === 0 || layout[i - 1][j] === '0') && (j === 0 || layout[i][j - 1] === '0') && (j === lenX || layout[i][j + 1] === '0') ? true :
                (j === 0 || layout[i][j - 1] === '1') && (j === lenX || layout[i][j + 1] === '0') && (i === 0 || layout[i - 1][j] === '0') && (i === lenY || layout[i + 1][j] === '0') ? true :
                    (j === lenX || layout[i][j + 1] === '1') && (j === 0 || layout[i][j - 1] === '0') && (i === 0 || layout[i - 1][j] === '0') && (i === lenY || layout[i + 1][j] === '0');
    };

    nmsp.util.isXYInJunctionCell = function (xyPair) {
        let ij = nmsp.util.getIJFromXY(xyPair);
        let i = ij[0], j = ij[1];
        let layout = nmsp.config.canvasLayout;
        return (i > 0 && layout[i - 1][j] === '1' || i < nmsp.config.canvasDivY - 1 && layout[i + 1][j] === '1') && (j > 0 && layout[i][j - 1] === '1' || j < nmsp.config.canvasDivX - 1 && layout[i][j + 1] === '1') ?
            true : (j > 0 && layout[i][j - 1] === '1' || j < nmsp.config.canvasDivX - 1 && layout[i][j + 1] === '1') && (i > 0 && layout[i - 1][j] === '1' || i < nmsp.config.canvasDivY - 1 && layout[i + 1][j] === '1');
    };

    nmsp.util.levelUp = function () {
        window.clearInterval(nmsp.gamePieceTimer);
        window.clearInterval(nmsp.opponentTimer);
        nmsp.timer.push(nmsp.gamePieceTimer = setInterval(() => {
            nmsp.gamePiece.move();
        }, nmsp.config.gamePieceStepInterval - ((nmsp.level - 1) * nmsp.config.levelUpSpeedChangeRate)));
        nmsp.timer.push(nmsp.opponentTimer = setInterval(() => {
            nmsp.moveOpponents();
        }, nmsp.config.opponentPieceStepInterval - ((nmsp.level - 1) * nmsp.config.levelUpSpeedChangeRate)));
    };

    nmsp.util.checkForCatch = function () {
        let opponentCount = nmsp.config.opponentPieceStart.length;
        for (let i = opponentCount - 1; i >= 0; i--) {
            let targetX = nmsp.gamePiece.x;
            let targetY = nmsp.gamePiece.y;
            let opponent = nmsp.opponentPiece[i];
            let x = opponent.x;
            let y = opponent.y;
            let xDif = targetX - x;
            let yDif = targetY - y;
            let diameter = (nmsp.config.gamePieceRadius - (nmsp.config.gamePieceSquareBuffer * 2)) * 2;

            if (Math.abs(xDif) < diameter && Math.abs(yDif) < diameter)
                nmsp.endPlay();
        }
    };

    nmsp.startScoring = function () {
        document.getElementById("levelBoard").innerHTML = `${++nmsp.level}`;
        nmsp.timer.push(setInterval(() => {
            document.getElementById("scoreBoard").innerHTML = `${nmsp.score++}`;
            document.getElementById("headingText").innerHTML = "";
            nmsp.checkForLevelUp();
        }, nmsp.config.scoreChangeInterval));
        return true;
    };

    nmsp.checkForLevelUp = function () {
        if (nmsp.score % nmsp.config.levelChangeScore === 0) {
            document.getElementById("levelBoard").innerHTML = `${++nmsp.level}`;
            nmsp.util.levelUp(nmsp.level);
        }
    };

    nmsp.moveOpponents = function () {
        let opponentCount = nmsp.config.opponentPieceStart.length;
        for (let i = opponentCount - 1; i >= 0; i--) {
            let opponent = nmsp.opponentPiece[i];
            opponent.move();
        }
    };

    nmsp.startPlay = function () {
        nmsp.startScoring();
        nmsp.playStarted = true;
        nmsp.util.levelUp();
        nmsp.timer.push(setInterval(() => {
            nmsp.util.checkForCatch();
        }, nmsp.config.catchCheckInterval));
    };

    nmsp.endPlay = function () {
        nmsp.util.unBindKeys();
        clearInterval(nmsp.gamePieceTimer);
        for (let i = nmsp.timer.length - 1; i >= 0; i--)
            clearInterval(nmsp.timer[i]);
        document.getElementById("headingText").innerHTML = "Game Over!";
    };

    nmsp.util.navigationKeyConfig = function (event) {
        switch (event.keyCode) {
            case 37:
                nmsp.gamePiece.setMovementDirection("left");
                break;
            case 38:
                nmsp.gamePiece.setMovementDirection("up");
                break;
            case 39:
                nmsp.gamePiece.setMovementDirection("right");
                break;
            case 40:
                nmsp.gamePiece.setMovementDirection("down");
                break;
            default:
                event.preventDefault();
                break;
        }
    };

    nmsp.util.unBindKeys = function () {
        document.removeEventListener("keydown", nmsp.util.navigationKeyConfig);
    };


    nmsp.init = function (config) {

        this.config = config;

        this.canvas = (() => {
            let canvas = document.getElementById("canvas");
            canvas.width = this.config.canvasWidth;
            canvas.height = this.config.canvasHeight;
            return canvas;
        })();

        this.gamePiece = ((x, y) => {
            let ctx = this.canvas.getContext("2d");
            ctx.beginPath();
            ctx.arc(x, y, this.config.gamePieceRadius, 0, 2 * Math.PI);
            ctx.fillStyle = this.config.gamePieceColour;
            ctx.fill();
            ctx.stroke();
            let piece = new this.Piece(x, y, this.config.gamePieceRadius, this.config.gamePieceColour);
            piece.ctx = ctx;
            return piece;
        })(nmsp.config.gamePieceStartPosition[0], nmsp.config.gamePieceStartPosition[1]);

        nmsp.gamePiece.move = function () {

            let obviousDirection = null;
            let isInJunctionCell;
            if ((!(isInJunctionCell = this.isExactlyInJunctionCell())) && this.isSetForReverse()) {
                this.reverseDirection();
            } else {
                if (isInJunctionCell) {
                    if (this.isFutureDirectionPossible()) {
                        this.currentDirection = this.futureDirection;
                    } else if (this.isExactlyInBentCell()) {
                        if (null !== (obviousDirection = this.getObviousDirection())) {
                            this.currentDirection = this.futureDirection = obviousDirection;
                        } else {
                            this.reverseDirection();
                        }
                    } else if (!this.isCurrentDirectionPossible()) {
                        this.reverseDirection();
                    }
                } else if (this.isExactlyInTerminalCell()) {
                    this.reverseDirection();
                }
            }
            if (null === this.currentDirection)
                this.currentDirection = this.futureDirection;

            this.takeOneStep(this.currentDirection);
        };

        this.opponentPiece.move = function () {
            let obviousDirection = null;
            let isInJunctionCell;
            if ((!(isInJunctionCell = this.isExactlyInJunctionCell())) && this.isSetForReverse()) {
                this.reverseDirection();
            } else {
                if (isInJunctionCell) {
                    this.futureDirection = this.computeBestDirection();
                    if (this.isFutureDirectionPossible()) {
                        this.currentDirection = this.futureDirection;
                    } else {
                        if (null !== (obviousDirection = this.getObviousDirection())) {
                            this.currentDirection = this.futureDirection = obviousDirection;
                        } else {
                            this.reverseDirection();
                        }
                    }
                }
            }
            if (null === this.currentDirection)
                if (null === this.futureDirection)
                    this.currentDirection = this.futureDirection = this.getPossibleRandomDirection();
                else
                    this.currentDirection = this.futureDirection;

            this.takeOneStep(this.currentDirection);
        };

        for (let i = this.config.opponentPieceStart.length - 1; i >= 0; i--)
            this.addOpponent(this.config.opponentPieceStart[i][0], this.config.opponentPieceStart[i][1]);

        this.canvas.drawCanvas = (() => {
            let layout = this.config.canvasLayout;
            let xUnit = this.config.canvasWidth / this.config.canvasDivX;
            let yUnit = this.config.canvasHeight / this.config.canvasDivY;
            let iLen = layout.length;
            for (let i = iLen - 1; i >= 0; i--) {
                let jLen = layout[i].length;
                for (let j = jLen - 1; j >= 0; j--)
                    if (layout[i][j] === '0')
                        this.util.createRestrictedZone(j, i, xUnit, yUnit);
            }
        })();

        this.canvas.bindKeys = (() => {
            document.addEventListener("keydown", this.util.navigationKeyConfig);
        })();

    };

})(cmiuc);

