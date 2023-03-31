import { aiPlayer, huPlayer, GameEngine } from "./bot_tic_tac_toe.js";

export class GameEngineFriends extends GameEngine {

    humanTurn() {
        return (e) => {
            const id = e.target.getAttribute('data-id');
          
            if (this.board[+id] != huPlayer && this.board[+id] != aiPlayer && !this.checkWinner(this.board, aiPlayer) && !this.checkWinner(this.board, huPlayer)) {
                if (this.turnCount % 2 == 0) {
                    this.board[+id] = huPlayer;
                    this.cellList[+id].classList.add('x');
                } else {
                    this.board[+id] = aiPlayer;
                    this.cellList[+id].classList.add('circle');
                }
              
                this.turnCount++;
              
                if (this.checkWinner(this.board, huPlayer)) {
                    this.win('Победа!', 'Победили крестики!');
                    return;
                }

                if (this.checkWinner(this.board, aiPlayer)) {
                    this.win('Победа!', 'Победили нолики!');
                    return;
                }

                if (this.turnCount >= this.limit) {
                    this.win('Ничья', 'Никто не победил');
                    return;
                }
            }
        }
    }

}

new GameEngineFriends();