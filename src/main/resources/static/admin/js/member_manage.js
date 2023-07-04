(() => {

    $(document).ready(function () {
        fetch('/members/all')
            .then(response => response.json())
            .then(json => {
                renderTable(json);
            });

        function renderTable(members) {
            $('#memberTable').DataTable({
                data: members,
                columns: [
                    {data: 'id'},
                    {data: 'name'},
                    {data: 'account'},
                    {data: 'birthday'},
                    {data: 'phone'},
                    {data: 'email'},
                    {data: 'address'},
                    {
                        data: 'access',
                        render: function (data, type, row) {
                            return getAccessText(data);
                        }
                    },
                    {
                        data: 'isEmailVerified',
                        render: function (data, type, row) {
                            return getEmailVerifiedText(data);
                        }
                    }
                ]
            });
        }

    });

    function getAccessText(access) {
        switch (access) {
            case '0':
                return '正常';
            case '1':
                return '⛔️停權';
        }
    }

    function getEmailVerifiedText(isVerified) {
        switch (isVerified) {
            case true:
                return '✅已驗證';
            case false:
                return '❌未驗證';
        }
    }
})();