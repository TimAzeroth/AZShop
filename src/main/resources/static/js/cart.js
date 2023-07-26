$(function() {
    let total = 0;
    $('.price').each(function() {
        total += parseInt($(this).text());
    });
    $('#total').text(total);
});