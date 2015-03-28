
angular.module('medekApp.controllers').controller('AuthController',[
'$scope',
'$rootScope',
'$translate',
'AuthService',
function($scope, $rootScope, $translate, AuthService) {
    $rootScope.user = {};
    $rootScope.isConnected = false; 
    $scope.loginError = '';
    $scope.language = '';
    $rootScope.authalerts = [];
    $scope.languages = [
        {name: 'English', key: 'en'},
        {name: 'Français', key: 'fr'}
    ];
    
    $scope.changeLanguage = function () {
        $translate.use($scope.language);
    };

    $scope.submitLogin = function() {
        AuthService.login($rootScope.user.login, $rootScope.user.password);
    };

    $scope.closeAlert = function(index) {
        $rootScope.authalerts.splice(index, 1);
    };
    
} ]);
