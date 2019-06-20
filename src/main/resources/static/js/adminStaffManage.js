var staffId;

$(document).ready(function() {
    if (sessionStorage.getItem("role")!="admin"){
        $("#staff-list").css("display","none");
        $("#staff-detail").css("display","none");
        alert("您暂时没有此项管理权限！")
    } else {
        $("#staff-list").css("display","");
        $("#staff-detail").css("display","none");
        getAllStaff();
    }

    $("#staff-form-btn").click(function () {
        var form = {
            name: $("#staff-name-input").val(),
            gender: $("#staff-gender-input").val(),
            nation: $("#staff-nation-input").val(),
            idnumber: $("#staff-idnumber-input").val(),
            birth: $("#staff-birth-input").val(),
            age: $("#staff-age-input").val(),
            hireDate: $("#staff-hire-date-input").val(),
            position: $("#staff-position-input").val(),
            phone: $("#staff-phone-input").val(),
            address: $("#staff-address-input").val(),
            userName: $("#staff-user-name-input").val(),
            password: $("#staff-password-input").val()
        };
        postRequest(
            '/staff/add',
            form,
            function (res) {
                if(res.success){
                    getAllStaff();
                    $("#staffModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    });

    $("#staff-edit-form-btn").click(function () {
        var form={
            id:staffId,
            name: $("#staff-edit-name-input").val(),
            gender: $("#staff-edit-gender-input").val(),
            nation: $("#staff-edit-nation-input").val(),
            idnumber: $("#staff-edit-idnumber-input").val(),
            birth: $("#staff-edit-birth-input").val(),
            age: $("#staff-edit-age-input").val(),
            hireDate: $("#staff-edit-hire-date-input").val(),
            position: $("#staff-edit-position-input").val(),
            phone: $("#staff-edit-phone-input").val(),
            address: $("#staff-edit-address-input").val(),
            userName: $("#staff-edit-user-name-input").val(),
            password: $("#staff-edit-password-input").val()
        };
        postRequest(
            "/staff/update",
            form,
            function (res) {
                if (res.success){
                    getDetail(staffId);
                    $("#staffEditModal").modal('hide');
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(error);
            }
        );
    });
});


function setStaffEditForm() {
    var form=getStaffNewForm();
    $("#staff-edit-name-input").val(form.name);
    $("#staff-edit-gender-input").val(form.gender);
    $("#staff-edit-nation-input").val(form.nation);
    $("#staff-edit-idnumber-input").val(form.idnumber);
    $("#staff-edit-birth-input").val(form.birth);
    $("#staff-edit-age-input").val(form.age);
    $("#staff-edit-hire-date-input").val(form.hireDate);
    $("#staff-edit-position-input").val(form.position);
    $("#staff-edit-phone-input").val(form.phone);
    $("#staff-edit-address-input").val(form.address);
    $("#staff-edit-user-name-input").val(form.userName);
    $("#staff-edit-password-input").val(form.password);
}
function getStaffNewForm() {
    return {
        name: $("#item-name").text(),
        gender: $("#item-gender").text(),
        nation: $("#item-nation").text(),
        idnumber: $("#item-id-number").text(),
        birth: $("#item-birth").text(),
        age: $("#item-age").text(),
        hireDate: $("#item-hire-date").text(),
        position: $("#item-position").text(),
        phone: $("#item-phone").text(),
        address: $("#item-address").text(),
        userName: $("#item-user-name").text(),
        password: $("#item-password").text()
    };
}

function getAllStaff() {
    getRequest(
        "/staff/all",
        function (res) {
            if (res.success) {
                renderStaff(res.content);
            } else {
                alert(res.content);
            }
        },
        function (error) {
            alert(error);
        });
}

function renderStaff(list) {
    $(".table tbody").empty();
    var staffDomStr="";
    list.forEach(function (staff) {
        staffDomStr+=
            "<tr id='"+staff.id+"' onclick='getDetail(this.id)'>" +
            "<td>"+staff.id+"</td>"+
            "<td>"+staff.name+"</td>"+
            "<td>"+staff.gender+"</td>"+
            "<td>"+staff.age+"</td>"+
            "<td>"+staff.position+"</td>"+
            "</tr>";
    });
    $(".table tbody").append(staffDomStr);
}

function getDetail(id) {
    $("#staff-list").css("display","none");
    $("#staff-detail").css("display","");
    staffId=id;
    getRequest(
        "/staff/info?staffId="+staffId,
        function (res) {
            if (res.success){
                renderStaffDetail(res.content);
            } else {
                alert(res.content);
            }
        },
        function (error) {
            alert(error);
        }
   );


}
function renderStaffDetail(staff) {
    $("#item-name").text(staff.name);
    $("#item-gender").text(staff.gender);
    $("#item-nation").text(staff.nation);
    $("#item-id-number").text(staff.idnumber);
    $("#item-birth").text(staff.birth);
    $("#item-age").text(staff.age);
    $("#item-hire-date").text(staff.hireDate);
    $("#item-position").text(staff.position);
    $("#item-phone").text(staff.phone);
    $("#item-address").text(staff.address);
    $("#item-user-name").text(staff.userName);
    $("#item-password").text(staff.password);
}

function returnToList() {
    getAllStaff();
    $("#staff-list").css("display","");
    $("#staff-detail").css("display","none");
}
function deleteStaff() {
    var r = confirm("确认要删除该员工信息吗");
    if (r) {
        deleteRequest(
            '/staff/delete?staffId=' + staffId,
            null,
            function (res) {
                if (res.success) {
                    $("#staff-list").css("display","");
                    $("#staff-detail").css("display","none");
                    getAllStaff();
                } else {
                    alert(res.message);
                }
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }
}



