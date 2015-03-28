
angular.module('medekApp.controllers').controller('HomeController',[
'$scope',
'$rootScope',
'$modal',
'$location',
'AuthService',
function($scope, $rootScope, $modal, $location, AuthService) {
    this.userLogin = $rootScope.user.login;

    $rootScope.logout = function () {
        AuthService.logout();
        $location.path('/');
        $location.replace();
    };
} ]);
