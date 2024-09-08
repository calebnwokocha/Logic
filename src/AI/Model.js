class Model {
    constructor() {
        this.memory = "";
    }

    generate(input) {
        this.memory += " " + input;
        const latinSquare = this.latinSquare();
        const stochasticValue = Math.floor(Math.random() * latinSquare.length);
        let output = "AI: ";
        for (let value = stochasticValue; value < latinSquare.length; value++) {
            output += latinSquare[value][value] + " ";
        }
        return output.trim();
    }

    latinSquare() {
        const words = this.memory.trim().split(/\s+/);
        const n = words.length;
        const latinSquare = Array.from({ length: n }, () => Array(n).fill(""));

        for (let row = 0; row < n; row++) {
                for (let col = 0; col < n; col++) {
                    latinSquare[row][col] = words[(row + col) % n];
                }
        }
        return latinSquare;
    }
}
