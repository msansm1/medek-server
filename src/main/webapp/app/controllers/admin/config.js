
angular.module('medekApp.controllers').controller('ConfigController',[
'$scope',
'$rootScope',
'$modal',
function($scope, $rootScope, $modal) {
    this.userLogin = $rootScope.user.login;

} ]);
