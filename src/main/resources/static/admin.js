const usersTableNavLink = document.getElementById("nav-home-tab");


function getData() {
    fetch("http://localhost:8080/admin/users").then(
        (res) => res.json()
    ).then((response) => {
        var tmpData = "";
        console.log(response);
        response.forEach((user) => {
            tmpData += "<tr>"
            tmpData += "<td>" + user.id + "</td>";
            tmpData += "<td>" + user.userName + "</td>";
            tmpData += "<td>" + user.email + "</td>";
            tmpData += "<td>" + user.roles.map((role) => role.name) + "</td>";
            tmpData += "<td><button class='btn btn-primary' onclick='editDataCall('" + user._id + "')'>Edit</button></td>";
            tmpData += "<td><button class='btn btn-danger' onclick='deleteData(`" + user._id + "`)'>Delete</button></td>";

            tmpData += "</tr>";
        })
        document.getElementById("tbData").innerHTML = tmpData;
    })
}

getData();

//Форма добавления юзера
const addUserForm = document.getElementById("add-form");
// Поля формы добавления нового юзера
const FormName = document.getElementById("add-username");
const FormPassword = document.getElementById("add-password");
const FormEmail = document.getElementById("add-email");

const FormRoles = document.getElementById("add-roles");
//Кнопка submit формы нового юзера
const addButtonSubmit = document.getElementById("add-btn-submit");

addUserForm.addEventListener("submit", (e) => {
    e.preventDefault();
    fetch("http://localhost:8080/admin/create", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            userName: FormName.value,
            password: FormPassword.value,
            email: FormEmail.value,
            // roles: FormRoles.value,

        })
    })
        .then(() => {
            usersTableNavLink.click();
            getData();
        });
})