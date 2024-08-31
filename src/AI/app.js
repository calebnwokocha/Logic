document.addEventListener("DOMContentLoaded", () => {
    const model = new Model();
    const inputText = document.getElementById("inputText");
    const outputArea = document.getElementById("output");
    const submitButton = document.getElementById("submitButton");

    submitButton.addEventListener("click", () => {
        const text = inputText.value.trim();
        if (text) {
            const output = model.generate(text);
            const userText = `<div>You: ${text}</div>`;
            const aiText = `<div>${output}</div>`;
            outputArea.innerHTML += userText + aiText;
            outputArea.scrollTop = outputArea.scrollHeight;
            inputText.value = "";
        }
    });

    inputText.addEventListener("keypress", (event) => {
        if (event.key === "Enter") {
            submitButton.click();
        }
    });
});
