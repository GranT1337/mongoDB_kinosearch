
$(document).ready(function () {
    $(".fa-trash-o").click(function() {
            var $call = $(this);
            $.ajax({
                type: 'GET',
                url: '/deleteFilm',
                data: {
                    'id': $call.val(),
                }
            }).done(
                (answer) -> {
                    if (answer == true) {
                        $call.closest('tr').remove();
                    }
                }
            )
        })
})

$(document).ready(function () {
    $(".fa-pencil").click(function() {
            var $call = $(this);
            $.ajax({
                type: 'GET',
                url: '/editFilm',
                data: {
                    'title': $call.closest('tr').find('.titleFilm').val(),
                    'producer': $call.closest('tr').find('.producerFilm').val(),
                    'points': $call.closest('tr').find('.pointsFilm').val(),
                    'id': $call.val(),
                }
            }).done(
                function (answer) {
                    if (answer == true) {
                        $call.closest('tr').find('.answer').html("Успех");
                    }
                }
            )
        })
}
)

