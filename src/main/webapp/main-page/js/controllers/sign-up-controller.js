/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('SignUpController',
    function ($scope, $location, AuthService) {
        $scope.newUser = {};
        $scope.save = function () {
            AuthService.signUp($scope.newUser)
                .success(function () {
                    alert('Вы зарегистрировались в системе! Вы получите пароль на почту в течение минуты.');
                    $location.path('#/aboutCurrentEvent')
                });
        };

        $scope.emailMsg = null;

        $scope.validateEmail = function(){
            AuthService.checkEmail($scope.newUser.email).success(function(data){
                if(data.status == 'OK'){
                    if(data.data == 'DUPLICATE'){
                        $scope.emailErrorMsg = 'На данную почту уже зарегистрирована учетная запись. ' +
                            'Если Вы забыли пароль, можете воспользоваться кнопкой "Восстановить пароль".';
                    }else{
                        $scope.emailErrorMsg = null;
                    }
                }
            });
        }

    });

