import { Grid } from "./grid.js";
import { Tile } from "./tile.js";

const mainURI = "https://76af-5-3-213-252.eu.ngrok.io/api/score";

const gameBoard = document.getElementById("game-board");

const grid = new Grid(gameBoard);
grid.getRandomEmptyCell().linkTile(new Tile(gameBoard));
grid.getRandomEmptyCell().linkTile(new Tile(gameBoard));

setupInputOnce();

function win() {
    document.getElementsByClassName("game-over")[0].classList.remove("hide");
    let text = `Вы набрали ${document.getElementsByClassName("score__counter")[0].textContent} очков`;
    document.getElementsByClassName("game-over__text")[0].innerHTML = text;

    addScore(document.getElementsByClassName("score__counter")[0].textContent);
}

async function addScore(num) {
    const url = new URL(window.location.href);
    const id = url.searchParams.get('id') ?? '0';
    axios.get(
        `${mainURI}/addScore/2048?score=${num}&id=${id}`,
    );  
}

// Tracking the pressed key
function setupInputOnce() {
    window.addEventListener("keydown", handleInput, { once: true });

    let touchStartX = 0;
    let touchEndX = 0;
    let touchStartY = 0;
    let touchEndY = 0;

    document.addEventListener("touchstart", function (event) {
        touchStartX = event.changedTouches[0].screenX;
        touchStartY = event.changedTouches[0].screenY;
    }, { once: true });

    document.addEventListener("touchend", function (event) {
        touchEndX = event.changedTouches[0].screenX;
        touchEndY = event.changedTouches[0].screenY;

        if (touchEndX != touchStartX || touchEndY != touchStartY) {
          checkDirection(touchStartX, touchEndX, touchStartY, touchEndY);
        } else {
          setupInputOnce();
        }
    }, { once: true });
}

async function checkDirection(touchStartX, touchEndX, touchStartY, touchEndY) {
    if (touchEndX < touchStartX && Math.abs(touchEndX - touchStartX) > Math.abs(touchEndY - touchStartY)) {
        if (!canMoveLeft()) {
            setupInputOnce();
            return;
        }
        await moveLeft();
    }

    if (touchEndX > touchStartX && Math.abs(touchEndX - touchStartX) > Math.abs(touchEndY - touchStartY)) {
        if (!canMoveRight()) {
            setupInputOnce();
            return;
        }
        await moveRight();
    }

    if (touchEndY < touchStartY && Math.abs(touchEndX - touchStartX) < Math.abs(touchEndY - touchStartY)) {
        if (!canMoveUp()) {
            setupInputOnce();
            return;
        }
        await moveUp();
    }

    if (touchEndY > touchStartY && Math.abs(touchEndX - touchStartX) < Math.abs(touchEndY - touchStartY)) {
        if (!canMoveDown()) {
            setupInputOnce();
            return;
        }
        await moveDown();
    }

    const newTile = new Tile(gameBoard);
    grid.getRandomEmptyCell().linkTile(newTile);

    if (!canMoveUp() && !canMoveDown() && !canMoveLeft() && !canMoveRight()) {
        await newTile.waitForAnimationEnd();
        win();
    }

    setupInputOnce();
}

// Tracking the pressed key
async function handleInput(event) {
    switch (event.key) {
    case "ArrowUp":
    case "w":
    case "ц":
        if (!canMoveUp()) {
            setupInputOnce();
            return;
        }
        await moveUp();
        break;

    case "ArrowDown":
    case "s":
    case "ы":
        if (!canMoveDown()) {
            setupInputOnce();
            return;
        }
        await moveDown();
        break;

    case "ArrowLeft":
    case "a":
    case "ф":
        if (!canMoveLeft()) {
            setupInputOnce();
            return;
        }
        await moveLeft();
        break;

    case "ArrowRight":
    case "d":
    case "в":
        if (!canMoveRight()) {
            setupInputOnce();
            return;
        }
        await moveRight();
        break;

    default:
        setupInputOnce();
        return;
    }

    const newTile = new Tile(gameBoard);
    grid.getRandomEmptyCell().linkTile(newTile);

    if (!canMoveUp() && !canMoveDown() && !canMoveLeft() && !canMoveRight()) {
        await newTile.waitForAnimationEnd();
        win();
    }

    setupInputOnce();
}

// Moving up
async function moveUp() {
    await slideTiles(grid.cellsGroupedByColumn);
}

// Moving down
async function moveDown() {
    await slideTiles(grid.cellsGroupedByReversedColumn);
}

// Moving left
async function moveLeft() {
    await slideTiles(grid.cellsGroupedByRow);
}

// Moving right
async function moveRight() {
    await slideTiles(grid.cellsGroupedByReversedRow);
}

// Moving tiles
async function slideTiles(groupedCells) {
    const promises = [];

    groupedCells.forEach((group) => slideTilesInGroup(group, promises));

    await Promise.all(promises);

    grid.cells.forEach((cell) => {
        cell.hasTileForMerge() && cell.mergeTiles();
    });
}

// Moving tiles
function slideTilesInGroup(group, promises) {
    for (let i = 1; i < group.length; i++) {
        if (group[i].isEmpty()) {
            continue;
        }

        const cellWithTile = group[i];

        let targetCell;
        let j = i - 1;

        while (j >= 0 && group[j].canAccept(cellWithTile.linkedTile)) {
            targetCell = group[j];
            j--;
        }
      
        if (!targetCell) {
            continue;
        }
      
        promises.push(cellWithTile.linkedTile.waitForTransitionEnd());
      
        if (targetCell.isEmpty()) {
            targetCell.linkTile(cellWithTile.linkedTile);
        } else {
            targetCell.linkTileForMerge(cellWithTile.linkedTile);
        }
      
        cellWithTile.unlinkTile();
  }
}

function canMoveUp() {
    return canMove(grid.cellsGroupedByColumn);
}

function canMoveDown() {
    return canMove(grid.cellsGroupedByReversedColumn);
}

function canMoveLeft() {
    return canMove(grid.cellsGroupedByRow);
}

function canMoveRight() {
    return canMove(grid.cellsGroupedByReversedRow);
}

function canMove(groupedCells) {
    return groupedCells.some((group) => canMoveInGroup(group));
}

function canMoveInGroup(group) {
    return group.some((cell, index) => {
      if (index === 0) {
          return false;
      }

      if (cell.isEmpty()) {
          return false;
      }

      const targetCell = group[index - 1];
      return targetCell.canAccept(cell.linkedTile);
    });
}
