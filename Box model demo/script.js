const box = document.getElementById("box");

const marginRange = document.getElementById("marginRange");
const borderRange = document.getElementById("borderRange");
const paddingRange = document.getElementById("paddingRange");

const marginValue = document.getElementById("marginValue");
const borderValue = document.getElementById("borderValue");
const paddingValue = document.getElementById("paddingValue");

marginRange.addEventListener("input", () => {
  box.style.margin = marginRange.value + "px";
  marginValue.textContent = marginRange.value + "px";
});

borderRange.addEventListener("input", () => {
  box.style.borderWidth = borderRange.value + "px";
  borderValue.textContent = borderRange.value + "px";
});

paddingRange.addEventListener("input", () => {
  box.style.padding = paddingRange.value + "px";
  paddingValue.textContent = paddingRange.value + "px";
});