/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EmailTemplatesController', ['$scope', 'EmailTemplatesService',
    function ($scope, EmailTemplatesService) {
        $scope.currentTemplate = {};
        $scope.isNew = true;


        $scope.getTemplates = function() {
            EmailTemplatesService.getTemplates().success(function(data){
                $scope.templates = data.data;
            });
        };

        $scope.save = function(){
            EmailTemplatesService.save($scope.currentTemplate).success(function(data){
                if(data.status == 'OK'){
                    $scope.getTemplates();
                }
            });
        };

        $scope.getTemplates();

        $scope.setCurrent = function(id){
            $scope.currentTemplate = $scope.templates[id];
            $scope.isNew = false;
        };

        $scope.createNew =function() {
            $scope.isNew = true;
            $scope.currentTemplate = {};
        }

    }
]);

