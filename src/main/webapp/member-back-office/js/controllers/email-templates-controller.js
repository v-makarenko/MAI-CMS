/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EmailTemplatesController', ['$scope', 'EmailTemplatesService',
    function ($scope, EmailTemplatesService) {
        $scope.currentTemplate = {};


        $scope.getTemplates = function() {
            EmailTemplatesService.getTemplates().success(function(data){
                $scope.templates = data;
            });
        };

        $scope.save = function(template){
            EmailTemplatesService.save(template);
        }

    }
]);

