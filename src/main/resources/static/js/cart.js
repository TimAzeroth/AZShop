$(function() {
    let total = 0;
    $('.price').each(function() {
        total += parseInt($(this).text());
    });
    $('#total').text(total);
    $('#totalinput').val(total);

    $('.productAmount').on('change', function() {
        const productId = $(this).attr('id').replace('amountSelect-', '');
        const amount = $(`#amountSelect-${productId}`).val();

        const data = {
            "product_id": productId,
            "amount": amount,
        };

        $.ajax({
            type: 'POST',
            url: '/cart/update',
            data: data,
            success: function() {
                return
            },
            error: function() {
                return
            }
        });

    });
});