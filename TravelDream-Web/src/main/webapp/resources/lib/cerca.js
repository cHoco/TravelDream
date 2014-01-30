/*
 * Initialize all the others
 */
$( '.js__datepicker' ).pickadate({
    format: 'dd/mm/yyyy',
    min: new Date()
})
$( '.js__timepicker' ).pickatime({
    format: 'HH:i'
})

