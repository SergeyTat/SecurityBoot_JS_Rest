// function getData() {
//     fetch("http://localhost:8080/test").then(
//         (res) => res.json()
//     ).then((response) => {
//         var tmpData = "";
//         console.log(response);
//         response.forEach((user) => {
//             tmpData += "<tr>"
//             tmpData += "<td>" + user.id + "</td>";
//             tmpData += "<td>" + user.userName + "</td>";
//             tmpData += "<td>" + user.email + "</td>";
//             tmpData += "<td>" + user.roles.map((role) => role.name) + "</td>";
//             tmpData += "<td><button class='btn btn-primary' onclick='editDataCall('" + user._id + "')'>Edit</button></td>";
//             tmpData += "<td><button class='btn btn-danger' onclick='deleteData(`" + user._id + "`)'>Delete</button></td>";
//
//             tmpData += "</tr>";
//         })
//         document.getElementById("tbData").innerHTML = tmpData;
//     })
// }
//
// getData();

console.log("Привет");