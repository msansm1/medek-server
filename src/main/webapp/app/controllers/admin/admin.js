
angular.module('medekApp.controllers').controller('AdminHomeController',[
'$scope',
'$rootScope',
'$modal',
'$location',
function($scope, $rootScope, $modal, $location) {
    this.userLogin = $rootScope.user.login;

} ]);
