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
      heightInInches: player.heightInInches,
      weightInPounds: player.weightInPounds,
      heightInCentimeters: player.heightInCentimeters,
      weightInKilograms: player.weightInKilograms,
      birthDate: player.birthDate,
      birthCity: player.birthCity.default,
      birthStateProvince: player.birthStateProvince?.default || "",
      birthCountry: player.birthCountry,
    }));
  } catch (error) {
    console.error(`Failed to fetch roster for team ${team} and season ${season}:`, error);
    return [];
  }
}

// Fetch player stats for all seasons
// async function fetchPlayerStats(playerId) {
//   try {
//     const response = await axios.get(`${NHL_API_BASE_URL}/${playerId}/landing`);
    
//     // Return the entire stats data for the player
//     return response.data.stats;
//   } catch (error) {
//     console.error("Error fetching player stats:", error);
//     return null;
//   }
// }


module.exports = { fetchRoster };
