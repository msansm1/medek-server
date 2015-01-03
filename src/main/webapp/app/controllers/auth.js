
angular.module('medekApp.controllers').controller('AuthController',[
'$scope',
'$rootScope',
'$translate',
'AuthService',
function($scope, $rootScope, $translate, AuthService) {
    $rootScope.user = {};
    $scope.loginError = '';
    $scope.language = '';
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
} ]);
