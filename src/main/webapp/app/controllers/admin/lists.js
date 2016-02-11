
angular.module('medekApp.controllers').controller('ListsController',[
'$scope',
'$rootScope',
'$modal',
'CollectionService',
'ArtistTypeService',
'BookTypeService',
'EditorService',
'GenreService',
'LangService',
'StoryGenreService',
'SupportService',
function($scope, $rootScope, $modal, CollectionService, ArtistTypeService, BookTypeService,
		EditorService, GenreService, LangService, StoryGenreService, SupportService) {
    this.userLogin = $rootScope.user.login;
    $scope.lists = [];
    
    var booktypes = {name : "booktypes", items : [] };
    BookTypeService.booktypes().then(
    	       function(response) {
    	           console.log("Booktypes : "+response.data);
    	           booktypes.items = response.data;
    	       }, function(reason) {
    	           alert('FAILED !!!');
    	       });
    $scope.lists.push(booktypes);
    
    var collections = {name : "collections", items : []};
    CollectionService.collections().then(
       function(response) {
           console.log("Collections : "+response.data);
           collections.items = response.data;
       }, function(reason) {
           alert('FAILED !!!');
       });
    $scope.lists.push(collections);
    
    var editors = {name : "editors", items : []};
    EditorService.editors().then(
    	       function(response) {
    	           console.log("Editors : "+response.data);
    	           editors.items = response.data;
    	       }, function(reason) {
    	           alert('FAILED !!!');
    	       });
    $scope.lists.push(editors);
    
    var genres = {name : "genres", items : []};
    GenreService.genres().then(
 	       function(response) {
 	           console.log("Genres : "+response.data);
 	           genres.items = response.data;
 	       }, function(reason) {
 	           alert('FAILED !!!');
 	       });
    $scope.lists.push(genres);
    
    var langages = {name : "langages", items : []};
    LangService.langs().then(
  	       function(response) {
  	           console.log("Langages : "+response.data);
  	           langages.items = response.data;
  	       }, function(reason) {
  	           alert('FAILED !!!');
  	       });
    $scope.lists.push(langages);
    
    var supports = {name : "supports", items : []};
    SupportService.supports().then(
  	       function(response) {
  	           console.log("Supports : "+response.data);
  	           supports.items = response.data;
  	       }, function(reason) {
  	           alert('FAILED !!!');
  	       });
     $scope.lists.push(supports);
    
    var storygenres = {name : "storygenres", items : []};
    StoryGenreService.storygenres().then(
  	       function(response) {
  	           console.log("StoryGenres : "+response.data);
  	           storygenres.items = response.data;
  	       }, function(reason) {
  	           alert('FAILED !!!');
  	       });
     $scope.lists.push(storygenres);

} ]);
