
angular.module('medekApp.controllers').controller('ConfigController',[
'$scope',
'$rootScope',
'$modal',
'ConfigService',
function($scope, $rootScope, $modal, ConfigService) {
    this.userLogin = $rootScope.user.login;

    $scope.config = [ ConfigService.config().then(
            function(response) {
                console.log("config : "+response.data);
                $scope.config = response.data;
            }, function(reason) {
                alert('FAILED !!!');
            }) ];
} ]);
