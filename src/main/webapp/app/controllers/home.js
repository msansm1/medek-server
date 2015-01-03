
angular.module('medekApp.controllers').controller('HomeController',[
'$scope',
'$rootScope',
'$modal',
'$stateParams',
'$translate',
function($scope, $rootScope, $modal, $stateParams, $translate) {
    this.userLogin = $rootScope.user.login;
    
} ]);
