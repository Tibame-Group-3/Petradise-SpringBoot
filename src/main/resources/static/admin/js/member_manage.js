
(() => {
    $(document).ready(function() {
        $('#memberTable').DataTable({
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
                { data: 'isEmailVerified' }
            ]
        });
    });

    function getAccessText(access) {
        switch (access) {
            case 0:
                return '正常';
            case 1:
                return '停權';
        }
    }
})();