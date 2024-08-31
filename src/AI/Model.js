class Model {
    constructor() {
        this.memory = "";
    }

    generate(input) {
        this.memory += " " + input;
        const latinSquare = this.latinSquare();
        const stochasticNum = Math.floor(Math.random() * latinSquare.length);
        let output = "AI: ";
        for (let i = 0; i < stochasticNum; i++) {
            output += latinSquare[stochasticNum][i] + " ";
        }
        return output.trim();
    }

    latinSquare() {
        const words = this.memory.trim().split(/\s+/);
        const n = words.length;
        const latinSquare = Array.from({ length: n }, () => Array(n).fill(""));

        for (let i = 0; i < n; i++) {
                for (let j = 0; j < n; j++) {
                    latinSquare[i][j] = words[(i + j) % n];
                }
        }
        return latinSquare;
    }
}
