
(() => {
    $(document).ready(function() {
        $('#memberTable').DataTable({
            serverSide: true,
            ajax: {
                url: '/members/all',
                type: 'GET'
            },
            columns: [
                { data: 'id' },
                { data: 'name' },
                { data: 'account' },
                { data: 'birthday' },
                { data: 'phone' },
                { data: 'email' },
                { data: 'address' },
                { data: 'access' },
                { data: 'bonus' },
                { data: 'isEmailVerified' }
            ]
        });
    });
})();