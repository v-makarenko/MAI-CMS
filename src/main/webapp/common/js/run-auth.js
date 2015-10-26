/**
 * Created by VMakarenko on 10/25/2015.
 */
runAuth = function ($rootScope, $location, AuthService) {
    AuthService.isAuthenticated().success(function (result) {
        $rootScope.authenticated = result.status == 'OK';
        var callback = function () {
            if (!$rootScope.authenticated) {
                window.location.href = 'index.html';
            } else {
                if (!$rootScope.currentUser) {
                    AuthService.getCurrentUser().success(function (data) {
                        if (data.status == 'OK') {
                            $rootScope.currentUser = data.data;
                            $rootScope.$broadcast('user.loaded')
                        }

                    });
                }
            }
        };

        $rootScope.$on('$routeChangeStart', function (event, next, current) {
            callback();
        });

        callback();

    });
}