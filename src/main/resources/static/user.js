const currentUserPanelData = document.getElementById("current_user_panel-data");
const currentAuthorisedUserData = document.getElementById("current_authorised_user-data");

let currentUser = () => {
    fetch ("http://localhost:8080/usertab", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .then(user => {
            if (user != null) {
                currentUserPanelData.innerHTML = `
                      <tr>
                        <td> ${user.id} </td>
                        <td> ${user.userName} </td>
                        <td> ${user.email} </td>
                        <td>${user.roles.map((role) => role.name)}</td>
                    </tr>
                        `
                currentAuthorisedUserData.innerHTML = `
                    <p class="text-light">${user.email} with roles: ${user.roles.map((role) => role.name)}</p>`
            }
        })
}
currentUser();