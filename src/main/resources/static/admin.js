function getData() {
    fetch("http://localhost:8080/test").then(
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
const addUserForm = document.querySelector(".add-user-form");
// Поля формы добавления нового юзера
const FormName = document.getElementById("add-user-form-name");
const FormLastName = document.getElementById("add-user-form-surname");
const FormAge = document.getElementById("add-user-form-age");
const FormEmail = document.getElementById("add-user-form-email");
const FormPassword = document.getElementById("add-user-form-password");
const FormRoles = document.getElementById("add-user-form-roles");
//Кнопка submit формы нового юзера
const addButtonSubmit = document.getElementById("add-btn-submit");