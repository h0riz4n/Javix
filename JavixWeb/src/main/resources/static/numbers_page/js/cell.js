let sum = 0;

export class Cell {
    constructor (gridElement, x, y) {
        const cell = document.createElement("div");
        cell.classList.add("cell");
        gridElement.append(cell);
        this.x = x;
        this.y = y;
    }

    // Saving a tile to the current cell
    linkTile(tile) {
        tile.setXY(this.x, this.y);
        this.linkedTile = tile;
    }

    // Deleting a tile from the current cell
    unlinkTile() {
        this.linkedTile = null;   
    }

    // Checking if cell is empty
    isEmpty() {
        return !this.linkedTile;
    }

    // Saving a tile to merge it
    linkTileForMerge(tile) {
        tile.setXY(this.x, this.y);
        this.linkedTileForMerge = tile;    
    }

    unlinkTileForMerge() {
        this.linkedTileForMerge = null;    
    }

    // Return true when tiles had already merged
    hasTileForMerge() {
        return !!this.linkedTileForMerge;    
    }

    // Can new tile merge with another or not
    canAccept(newTile) {
        return this.isEmpty() || (!this.hasTileForMerge() && this.linkedTile.value === newTile.value);    
    }

    // Merging cells
    mergeTiles() {
        this.linkedTile.setValue(this.linkedTile.value + this.linkedTileForMerge.value);
        sum += this.linkedTile.value;
        document.getElementsByClassName('score__counter')[0].innerHTML = sum;
        this.linkedTileForMerge.removeFromDOM();  
        this.unlinkTileForMerge();  
    }
}