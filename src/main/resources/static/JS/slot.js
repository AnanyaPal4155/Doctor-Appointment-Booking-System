// Load slots on page load
window.onload = loadSlots;

// ADD SLOT
document.getElementById("slotForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const date = document.getElementById("date").value;
    const time = document.getElementById("time").value;
    const capacity = document.getElementById("capacity").value;

    fetch(`/slot/add?date=${date}&time=${time}&capacity=${capacity}`, {
        method: "POST"
    })
    .then(res => res.json())
    .then(() => {
        alert("Slot added successfully");
        loadSlots();
        document.getElementById("slotForm").reset();
    })
    .catch(() => alert("Error adding slot"));
});

// LOAD ALL SLOTS
function loadSlots() {
    fetch("/slot/freeSlots")
        .then(res => res.json())
        .then(data => {

            const tbody = document.getElementById("slotTableBody");
            tbody.innerHTML = "";

            data.forEach(slot => {
                tbody.innerHTML += `
                    <tr>
                        <td>${slot.date}</td>
                        <td>${slot.time}</td>
                        <td>${slot.capacity}</td>
                        <td>${slot.bookedCount}</td>
                        <td>
                            <button onclick="deleteSlot(${slot.slotId})">
                                Delete
                            </button>
                        </td>
                    </tr>
                `;
            });
        });
}

function deleteSlot(id) {
    if (!confirm("Are you sure you want to delete this slot?")) return;

    fetch(`/slot/delete/${id}`, {
        method: "DELETE"
    })
    .then(res => {
        if (!res.ok) {
            return res.text().then(text => { throw new Error(text); });
        }
        loadSlots();
    })
    .catch(err => {
        alert(" Slot cannot be deleted because appointments are already booked.");
    });
}

