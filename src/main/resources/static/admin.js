const usersTableNavLink = document.getElementById("nav-home-tab");


function getData() {

    fetch("http://localhost:8080/admin/users").then((res) => res.json())
        .then((response) => {
            let tmpData = '';


            response.forEach((user) => {

                tmpData += `
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.userName}</td>
                            <td>${user.email}</td>
                           <td>${user.roles.map((role) => role.name)}</td>
                           <td> <button type="button" class="btn btn-info" id="btn-edit-modal-call" data-toggle="modal" data-target="modal-edit"
                    data-id="${user.id}">Edit</button></td>
                    <td> <button type="button" class="btn btn-danger" id="btn-delete-modal-call" 
                    data-id="${user.id}">Delete</button></td>
                                    </tr>`;


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


//Получение массива ролей из SELECT
function getRolesFromAddUserForm() {
    let roles = Array.from(FormRoles.selectedOptions)
        .map((s) => ({
            id: s.value, name: s.text
        }));

    return roles;
}


addUserForm.addEventListener("submit", (e) => {
    e.preventDefault();
    fetch("http://localhost:8080/admin/create", {
        method: 'POST', headers: {
            'Content-Type': 'application/json'
        }, body: JSON.stringify({
            userName: FormName.value,
            password: FormPassword.value,
            email: FormEmail.value,
            roles: getRolesFromAddUserForm(),

        })
    })
        .then(() => {
            usersTableNavLink.click();
            location.reload();
        });
})

//Изменение пользователя

const modalEditExitBtn = document.getElementById("exit_btn-modal-edit");
const modalEditCloseBtn = document.getElementById("close_btn-modal-edit");
const modalEditSubmitBtn = document.getElementById("submit_btn-modal-edit");
const editUsersRoles = document.getElementById("edit-rolesSelect");
const editRoleAdminOption = document.getElementById("edit-role_admin");
const editRoleUserOption = document.getElementById("edit-role_user");

//Удаление пользователя
const deleteRoleAdminOption = document.getElementById("delete-role_admin");
const deleteRoleUserOption = document.getElementById("delete-role_user");
const modalDeleteSubmitBtn = document.getElementById("submit_btn-modal-delete");
const modalDeleteExitBtn = document.getElementById("exit_btn-modal-delete");
const modalDeleteCloseBtn = document.getElementById("close_btn-modal-delete");


function getRolesFromEditUserForm() {

    let roles = Array.from(editUsersRoles.selectedOptions)
        .map((s) => ({
            id: s.value, name: s.text
        }));

    return roles;

}

//Отслеживание нажатий по кнопкам Edit и Delete в таблице юзеров
document.getElementById("tbData").addEventListener("click", e => {
    e.preventDefault();
    let delButtonIsPressed = e.target.id === 'btn-delete-modal-call';
    let editButtonIsPressed = e.target.id === 'btn-edit-modal-call';

//Удаление пользователей

    const deleteUsersId = document.getElementById("delete-id")
    const deleteUsersName = document.getElementById("delete-username")
    const deleteUsersEmail = document.getElementById("delete-email")

    if (delButtonIsPressed) {

        let currentUserId = e.target.dataset.id;

        fetch("http://localhost:8080/admin" + "/" + currentUserId, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            }
        })
            .then(res => res.json())
            .then(user => {
                deleteUsersId.value = user.id;
                deleteUsersName.value = user.userName;
                deleteUsersEmail.value = user.email;

                let deleteRoles = user.roles.map(i => i.name)

                deleteRoles.forEach(
                    role => {

                        if (role === "ROLE_ADMIN") {
                            deleteRoleAdminOption.setAttribute('selected', "selected");

                        } else if (role === "ROLE_USER") {
                            deleteRoleUserOption.setAttribute('selected', "selected");
                        }
                    })
            })

        $('#modal-delete').modal('show');

        modalDeleteSubmitBtn.addEventListener("click", e => {
            e.preventDefault();
            fetch("http://localhost:8080/admin" + "/" + currentUserId, {
                method: 'DELETE',
            })
            modalDeleteExitBtn.click();
            location.reload();
        })
    }


//Изменение

    const editUsersId = document.getElementById("edit-id");
    const editUsersName = document.getElementById("edit-username");
    const editUsersPassword = document.getElementById("edit-password");
    const editUsersEmail = document.getElementById("edit-email");

    if (editButtonIsPressed) {
        let currentUserId = e.target.dataset.id;
        fetch("http://localhost:8080/admin" + "/" + currentUserId, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            }
        })
            .then(res => res.json())
            .then(user => {

                editUsersId.value = user.id;
                editUsersName.value = user.userName;
                editUsersPassword.value = '';
                editUsersEmail.value = user.email;
                let editRoles = user.roles.map(i => i.name)

                editRoles.forEach(
                    role => {
                        if (role === "ROLE_ADMIN") {
                            editRoleAdminOption.setAttribute('selected', "selected");

                        } else if (role === "ROLE_USER") {
                            editRoleUserOption.setAttribute('selected', "selected");
                        }
                    })
            })
        $('#modal-edit').modal('show');

        modalEditSubmitBtn.addEventListener("click", e => {
            e.preventDefault();
            let user = {
                id: editUsersId.value,
                userName: editUsersName.value,
                password: editUsersPassword.value,
                email: editUsersEmail.value,
                roles: getRolesFromEditUserForm()
            }
            console.log(user);
            console.log(user.roles);
            fetch("http://localhost:8080/admin/create", {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                body: JSON.stringify(user)
            })
                .then(() => location.reload());

        })

    }

})