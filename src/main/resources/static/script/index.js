$("#user-form").submit(function(event){
    event.preventDefault();
    const $form = $(this);
    const url = 'http://localhost:8081/update';
    const productId = $form.find('input[name="id"]').val();
    const productName = $form.find('input[name="name"]').val();
    const productPrice = $form.find('input[name="price"]').val();

    $.ajax({
        type : 'PUT',
        url : url,
        contentType: 'text/html',
        data : {name: productName, price: productPrice, id: productId },
        error : function(xhr, status, error){
            $('#msg').html('<span style=\'color:red;\'>'+error+'</span>')
        }
    });
});
