/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EmailDistrController', ['$scope', 'EmailService',
    function ($scope, EmailService) {
        $scope.distribute = function () {
            EmailService.distribute().success(function(){
                alert('OK');
            })
        }

    }
]);

