const axios = require("axios");

const NHL_API_BASE_URL = "https://api-web.nhle.com/v1";

// Fetch the team roster for the specific team and season
async function fetchRoster(team, season) {
  try {
    const response = await axios.get(`${NHL_API_BASE_URL}/roster/${team}/${season}`);
    
    // Combine forwards, defensemen, and goalies into a single array
    const players = [
      ...response.data["forwards"] || [],
      ...response.data["defensemen"] || [],
      ...response.data["goalies"] || []
    ];

    // Map the players to the Player type structure
    return players.map(player => ({
      id: player.id,
      headshot: player.headshot,
      firstName: player.firstName.default,
      lastName: player.lastName.default,
      sweaterNumber: player.sweaterNumber,
      positionCode: player.positionCode,
      shootsCatches: player.shootsCatches,

    }));
  } catch (error) {
    console.error(`Failed to fetch roster for team ${team} and season ${season}:`, error);
    return [];
  }
}

async function fetchPlayerInfo(playerId) {
  try {
    const response = await axios.get(`${NHL_API_BASE_URL}/player/${playerId}/landing`);
    //print the response
    // console.log(response.data);
    const playerInfo = response.data;
    const teamNameToAbbreviation = {
      "Anaheim Ducks": "ANA",
      "Arizona Coyotes": "ARI",
      "Boston Bruins": "BOS",
      "Buffalo Sabres": "BUF",
      "Calgary Flames": "CGY",
      "Carolina Hurricanes": "CAR",
      "Chicago Blackhawks": "CHI",
      "Colorado Avalanche": "COL",
      "Columbus Blue Jackets": "CBJ",
      "Dallas Stars": "DAL",
      "Detroit Red Wings": "DET",
      "Edmonton Oilers": "EDM",
      "Florida Panthers": "FLA",
      "Los Angeles Kings": "LAK",
      "Minnesota Wild": "MIN",
      "Montréal Canadiens": "MTL",
      "Nashville Predators": "NSH",
      "New Jersey Devils": "NJD",
      "New York Islanders": "NYI",
      "New York Rangers": "NYR",
      "Ottawa Senators": "OTT",
      "Philadelphia Flyers": "PHI",
      "Pittsburgh Penguins": "PIT",
      "San Jose Sharks": "SJS",
      "Seattle Kraken": "SEA",
      "St. Louis Blues": "STL",
      "Tampa Bay Lightning": "TBL",
      "Toronto Maple Leafs": "TOR",
      "Vancouver Canucks": "VAN",
      "Vegas Golden Knights": "VGK",
      "Washington Capitals": "WSH",
      "Winnipeg Jets": "WPG"
    };


    const seasonTotals = Array.isArray(playerInfo.seasonTotals) ? playerInfo.seasonTotals
    .filter(season => season.gameTypeId === 2 && season.leagueAbbrev === "NHL")
    .map(season => ({
      season: season.season,
      teamName: teamNameToAbbreviation[season.teamName.default] || season.teamName.default,
      goals: season.goals,
      assists: season.assists,
      gamesPlayed: season.gamesPlayed,
      points: season.points,
      leagueAbbrev: season.leagueAbbrev,
      gameTypeId: season.gameTypeId
  })) : [];
    // Map the playerInfo the playerinfo type structure
    return {
      playerId: playerInfo.playerId,
      headshot: playerInfo.headshot,
      firstName: playerInfo.firstName.default,
      lastName: playerInfo.lastName.default,
      sweaterNumber: playerInfo.sweaterNumber,
      position: playerInfo.position,
      shootsCatches: playerInfo.shootsCatches,
      heightInInches: playerInfo.heightInInches,
      weightInPounds: playerInfo.weightInPounds,
      birthDate: playerInfo.birthDate,
      birthCity: playerInfo.birthCity.default,
      birthStateProvince: playerInfo.birthStateProvince ? playerInfo.birthStateProvince.default : null,
      birthCountry: playerInfo.birthCountry,
      seasonTotals: seasonTotals
    };
  }
  catch (error) {
    console.error(`Failed to fetch player info for player ${playerId}:`, error);
    return [];
  }


}


module.exports = { fetchRoster, fetchPlayerInfo };
