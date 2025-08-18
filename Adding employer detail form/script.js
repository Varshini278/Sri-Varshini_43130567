const form = document.getElementById("employerForm");
const tableBody = document.querySelector("#employerTable tbody");
const addBtn = document.getElementById("addBtn");
const editIndexField = document.getElementById("editIndex");

let employers = [];

form.addEventListener("submit", function(e) {
  e.preventDefault();

  const name = document.getElementById("name").value.trim();
  const age = document.getElementById("age").value.trim();
  const position = document.getElementById("position").value.trim();
  const editIndex = editIndexField.value;

  if (name && age && position) {
    if (editIndex === "") {
      // Add new employer
      if (employers.length < 10) {
        employers.push({ name, age, position });
      } else {
        alert("Maximum 10 employers can be added!");
      }
    } else {
      // Update existing employer
      employers[editIndex] = { name, age, position };
      editIndexField.value = "";
      addBtn.textContent = "Add Employer";
    }
    updateTable();
    form.reset();
  }
});

function updateTable() {
  tableBody.innerHTML = "";
  employers.forEach((emp, index) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${index + 1}</td>
      <td>${emp.name}</td>
      <td>${emp.age}</td>
      <td>${emp.position}</td>
      <td>
        <button class="action-btn edit-btn" onclick="editEmployer(${index})">Edit</button>
        <button class="action-btn delete-btn" onclick="deleteEmployer(${index})">Delete</button>
      </td>
    `;
    tableBody.appendChild(row);
  });
}

function editEmployer(index) {
  const emp = employers[index];
  document.getElementById("name").value = emp.name;
  document.getElementById("age").value = emp.age;
  document.getElementById("position").value = emp.position;
  editIndexField.value = index;
  addBtn.textContent = "Update Employer";
}

function deleteEmployer(index) {
  if (confirm("Are you sure you want to delete this employer?")) {
    employers.splice(index, 1);
    updateTable();
  }
}
