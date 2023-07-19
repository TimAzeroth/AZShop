if(user.role === 'adminId') {
    // Display form to reply to reviews
    // You may want to include the review ID or some other identifier
    // So that the server knows which review you're replying to.
    response.write('<form action="/replyToReview" method="POST">');
    response.write('<input type="hidden" name="reviewId" value="' + review.id + '" />');
    response.write('<textarea name="replyContent"></textarea><br />');
    response.write('<input type="submit" value="Submit Reply" />');
    response.write('</form>');
}
else {
    // Don't display the form
    // You may want to include a message explaining why
    response.write('<p>You must be an adminId to reply to reviews.</p>');
}
