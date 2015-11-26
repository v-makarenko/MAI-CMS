/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EditThesisModalController', ['$scope', '$rootScope', '$routeParams', '$modal', '$modalInstance', 'ThesisService', 'SectionsService', 'id', 'FileUploader',
    function ($scope, $rootScope, $routeParams, $modal, $modalInstance, ThesisService, SectionsService, id, FileUploader) {
        //ThesisService
        //    .save({})
        //    .success(function (data) {
        //        if (data.status == 'OK') {
        //            $scope.currentThesis = data.data;
        //        }
        //    });

        $scope.uploader = new FileUploader({
            url: 'api/private/files'
        });

        $scope.eventId = $routeParams.id;

        $scope.currentThesis = {};

        $scope.ok = function () {
            $scope.currentThesis.userId = $rootScope.currentUser.id;
            $scope.currentThesis.eventId = $scope.eventId;
            ThesisService.save($scope.currentThesis).success(function () {
                $modalInstance.close();
            });
        };
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.addCoauthor = function () {
            var addCoauthorModal = $modal.open({
                templateUrl: 'member-back-office/html/fragments/modal/add-coauthor-modal.html',
                controller: 'AddCoauthorModalController',
                //size: size,
                resolve: {
                    data: function () {
                        return {}
                    }

                }
            });
            addCoauthorModal.result.then(function (data) {
                if (!$scope.currentThesis.coauthorsList) {
                    $scope.currentThesis.coauthorsList = [];
                }
                $scope.currentThesis.coauthorsList.push(data);
            });
        };

        $scope.deleteCoauthor = function (id) {
            ThesisService.delete(id).success(function (data) {
                if (data.status == 'OK') {
                    $scope.currentThesis.coauthorsList.delete(_.indexOf($scope.currentThesis.coauthorsList, {id: id}));
                }
            })
        };

        SectionsService.getAll($routeParams.id).success(function (data) {
            if (data.status === 'OK') {
                $scope.sections = data.data;
            }
        });

        if (id) {
            ThesisService.find(id).success(function (data) {
                if (data.status == 'OK') {
                    $scope.currentThesis = data.data;
                }
            })
        }

    }
]);

