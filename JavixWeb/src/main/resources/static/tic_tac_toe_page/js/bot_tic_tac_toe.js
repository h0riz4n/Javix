const game = document.getElementById('game');
const resetBtn = document.getElementById('reload');

export const aiPlayer = 'O'; 
export const huPlayer = 'X';

const mainURI = "https://76af-5-3-213-252.eu.ngrok.io/api/score";

export class GameEngine {

    constructor(size = 3) {
        this.size = size;
        this.turn = Math.floor(Math.random() * 2);
        this.turnCount = 0;

        resetBtn.addEventListener('click', () => {this.resetGame();});

        this.cellList = [];
        this.resetGame();
    }

    get limit() {
        return this.size * this.size;
    }

    init() {
        for (let i = 0; i < this.limit; i++) {
            const cell = document.createElement('div');
            cell.classList.add('cell');
            cell.setAttribute('data-id', i);
            cell.addEventListener('click', this.humanTurn());
            game.appendChild(cell);
            this.cellList.push(cell);
        }
    }

    resetGame() {
        this.board = [...Array(this.size * this.size).keys()];
        this.cellList = [];
        document.getElementsByClassName("game-over")[0].classList.add("hide");
        game.innerHTML = '';
        this.turnCount = 0;
        this.init();
    }

    humanTurn() {
        return (e) => {
            const id = e.target.getAttribute('data-id');
          
            if (this.board[+id] != huPlayer && this.board[+id] != aiPlayer && !this.checkWinner(this.board, aiPlayer) && !this.checkWinner(this.board, huPlayer)) {
                this.turnCount += 1;
                this.board[+id] = huPlayer;
                this.cellList[+id].classList.add('x');
              
                if (this.checkWinner(this.board, huPlayer)) {
                    this.win('Вы выиграли', 'Вам начислено 10 очков');
                    this.addScore(10);
                    return;
                }

                if (this.turnCount >= this.limit) {
                    this.win('Ничья', 'Вам начислено 5 очков');
                    this.addScore(5);
                    return;
                }
              
                this.makeAiTurn();
            }
        }
    }

    async addScore(num) {
        const url = new URL(window.location.href);
        const id = url.searchParams.get('id') ?? '0';
        axios.get(
            `${mainURI}/addScore/tic_tac_toe?score=${num}&id=${id}`,
        );  
    }

    makeAiTurn() {
        this.turnCount += 1
        const bestMove = this.minimax(this.board, aiPlayer);
        this.board[bestMove.idx] = aiPlayer;
        this.cellList[bestMove.idx].classList.add('circle');

        if (this.checkWinner(this.board, aiPlayer)) {
            this.win('Я выиграл', 'Вы ничего не получаете');
            return;
        }

        if (this.turnCount >= this.limit) {
            this.win('Ничья', 'Вам начислено 5 очков');
            this.addScore(5);
            return;
        }
    }

    checkWinner(board, player) {
        for (let i = 0; i <= 6; i+= 3) {
            if (board[i] === player && board[i+1] === player && board[i+2] === player) {
                return true;
            }
        }

        for (let i = 0; i < 3; i++) {
            if (board[i] === player && board[i+3] === player && board[i+6] === player) {
                return true;
            }
        }

        if (
            board[0] === player && board[4] === player && board[8] === player ||
            board[2] === player && board[4] === player && board[6] === player
            ) {
              return true;
        }

        return false;
    }

    minimax(board, player) {
        const emptyCells = this.findEmptyCells(board);

        if (this.checkWinner(board, huPlayer)) {
            return {score: -1};
        } else if (this.checkWinner(board, aiPlayer)) {
            return {score: 1};
        } else if (emptyCells.length === 0) {
            return {score: 0};
        }

        let moves = [];

        for (let i = 0; i < emptyCells.length; i++) {
            let move = [];
            board[emptyCells[i]] = player;
            move.idx = emptyCells[i];

            if (player === huPlayer) {
                const payload = this.minimax(board, aiPlayer);
                move.score = payload.score;
            }

            if (player === aiPlayer) {
                const payload = this.minimax(board, huPlayer);
                move.score = payload.score;
            }

            board[emptyCells[i]] = move.idx;
            moves.push(move);
        }

        let bestMove = null;

        if (player === aiPlayer) {
            let bestScore = -Infinity
            for (let i = 0; i < moves.length; i++) {
                if (moves[i].score > bestScore) {
                    bestScore = moves[i].score
                    bestMove = i 
                }
            }
        } else {
            let bestScore = Infinity
            for (let i = 0; i < moves.length; i++) {
                if (moves[i].score < bestScore) {
                    bestScore = moves[i].score
                    bestMove = i 
                }
            }
        }

        return moves[bestMove];
    }

    findEmptyCells(board) {
        return board.filter(c => c !== huPlayer && c !== aiPlayer);
    }

    win(title, text) {
        document.getElementsByClassName("game-over")[0].classList.remove("hide");
        document.getElementsByClassName("game-over__title")[0].innerHTML = title;
        document.getElementsByClassName("game-over__text")[0].innerHTML = text;
    }
}