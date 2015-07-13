/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('MenuEditController', ['$scope', '$rootScope', 'MenuEditService', '$routeParams', '$location',
    function ($scope, $rootScope, MenuEditService, $routeParams, $location) {
        $rootScope.$broadcast('main.tabSelected', 'menu');


        $scope.goToEditMenu = function () {
            $location.path('/menuEdit/true');
        };

        // working with menu items

        $scope.editMenuItem = function (index) {
            if (index === undefined) {
                $scope.currentMenuItem = {
                    editMode: false
                };
            } else {
                $scope.currentMenuItem = angular.copy($scope.menuItems[index]);
                $scope.currentMenuItem.editMode = true;
            }
            $('#menu-edit-modal').modal('show');
        };



        $scope.saveItem = function () {
            if (!$scope.currentMenuItem.editMode) {
                MenuEditService.insert($scope.currentMenuItem).success(function(){
                    $scope.getAll();
                });
            } else {
                MenuEditService.update($scope.currentMenuItem).success(function(){
                    $scope.getAll();
                });
            }
        };

        $scope.deleteMenuItem = function (index) {
            MenuEditService.delete($scope.menuItems[index].id).success(function(){
                $scope.getAll();
            });
        };

        $scope.deleteItem = function (id) {
            MenuEditService.delete(id);
        };

        $scope.setNewPosition = function (id, afterId) {
            MenuEditService.setAfter(id, afterId);
        };

        $scope.getAll = function () {
            var filter = {};
            //filter.companyId = window.userData.companyId;
            MenuEditService.getAll(filter).success(function (data) {
                $scope.menuItems = data.data;
            });
        };

        $scope.getAll();


        $scope.submitMenuEdit = function () {
            if ($scope.currentMenuItem.editMode) {
                MenuEditService.update($scope.currentMenuItem);
            } else {
                MenuEditService.insert($scope.currentMenuItem);
            }
        };

        // working with menu custom items
        $scope.deleteCustomMenuItem = function (index) {
            $scope.currentMenuItem.customs.splice(index, 1);
        };

        $scope.editCustomMenuItem = function (index) {
            if (index === undefined) {
                $scope.currentCustomMenuItem = {
                    editMode: false
                };
            } else {
                $scope.currentCustomMenuItem = angular.copy($scope.currentMenuItem.customs[index]);
                $scope.currentCustomMenuItem.editMode = true;
            }
            $('#menu-edit-custom-modal').modal('show');
        };

        $scope.addCustomMenuItem = function () {
            if($scope.currentMenuItem.customs === undefined){
                $scope.currentMenuItem.customs = [];
            }
            _.forEach($scope.currentMenuItem.customs, function(item){
                item.editing = false;
            });
            $scope.currentMenuItem.customs.push({
                editing: true
            });
        };

        // working with custom menu
        $scope.addCustomMenuItemVariant = function (index) {
            var custom = $scope.currentMenuItem.customs[index];
            if(custom.variants === undefined){
                custom.variants = [];
            }
            _.forEach(custom.variants, function(item){
                item.editing = false;
            });
            custom.variants.push({
                editing: true
            });
        };
        $scope.deleteCustomMenuItemVariant = function(parentIndex, index){
            $scope.currentMenuItem.customs[parentIndex].variants.splice(index, 1);
        };

        $scope.menuEditMode = !($routeParams.edit == undefined || $routeParams.edit == "false")

        MenuEditService.getAllWeekDays().success(function(data){
            $scope.weekDays = data;
        });

        $scope.m

    }]);

