/**
 * Created by SaiVedNish on 8/1/2017.
 */
function insertData() {
    var data = fetchFormData();
    $.ajax({
        type: 'POST',
        url: "/api/v1/customer",
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            $('#details').html(data);
        },
        error: function (error) {
            if (error.status == 400){
                $('#details').html("Server got a bad request. Please check payload data.")
            }
            else {
                $('#details').html(error.responseText);
            }
        }
    });
}

function fetchAll() {
    $.get("/api/v1/customers", function(data, status){
        var res = JSON.stringify(data, null, 2);
        $('#details').html(res);
    });
}

function fetchById() {
    var id = $('#id').val();
    $.ajax({
        type: 'GET',
        url: "/api/v1/customer/" + id,
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            var res = JSON.stringify(data, null, 2);
            $('#id').val(data.id);
            $('#age').val(data.age);
            $('#name').val(data.name);
            $('#desc').val(data.description);
            $('#email').val(data.email);
            $('#city').val(data.city);
            $('#zipcode').val(data.zipcode);
            $('#details').html(res);
        },
        error: function (error) {
            if (error.status == 404) {
                $('#details').html("Customer with Id " + id + " does not exist");
            }
        }
    });
}

function updateData() {
    var data = fetchFormData();
    $.ajax({
        type: 'PUT',
        url: "/api/v1/customer/" + $('#id').val(),
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            $('#details').html(data);
        },
        error: function (error) {
            if (error.status == 400){
                $('#details').html("Server got a bad request. Please check payload data.")
            }
            else {
                $('#details').html(error.responseText);
            }
        }
    });
}

function deleteData() {
    $.ajax({
        type: 'DELETE',
        url: "/api/v1/customer/" + $('#id').val(),
        contentType: 'application/json',
        success: function (data) {
            $('#details').html(data);
        },
        error: function (error) {
            $('#details').html(error.responseText);
        }
    });
}

function fetchFormData() {
    var data = {}
    data.id = $('#id').val();
    data.age = $('#age').val();
    data.name = $('#name').val();
    data.description = $('#desc').val();
    data.email = $('#email').val();
    data.city = $('#city').val();
    data.zipcode = $('#zipcode').val();
    return data;
}
