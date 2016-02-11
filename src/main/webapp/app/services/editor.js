angular.module('medekApp.services').service('EditorService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.editors = function() {
        return $http.get('services/editors');
    };
    
    this.editor = function(id) {
        return $http.get('services/editors/'+id);
    };
    
    this.createUpdate = function(editor) {
        return $http.post('services/editors', editor);
    };
} ]);