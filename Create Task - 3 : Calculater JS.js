const display = document.getElementById('display');
const digitButtons = document.querySelectorAll('.digit');
const operatorButtons = document.querySelectorAll('.operator');
const equalsButton = document.getElementById('equals');
const clearButton = document.getElementById('clear');

let currentInput = '';
let operator = '';

digitButtons.forEach(button => {
    button.addEventListener('click', () => {
        currentInput += button.textContent;
        display.value = currentInput;
    });
});

operatorButtons.forEach(button => {
    button.addEventListener('click', () => {
        operator = button.textContent;
        currentInput = '';
    });
});

equalsButton.addEventListener('click', () => {
    if (currentInput && operator) {
        const result = calculateResult(parseFloat(currentInput), operator);
        display.value = result;
        currentInput = result.toString();
    }
});

clearButton.addEventListener('click', () => {
    currentInput = '';
    operator = '';
    display.value = '';
});

function calculateResult(num1, op) {
    switch (op) {
        case '+':
            return num1 + parseFloat(currentInput);
        default:
            return num1;
    }
}
