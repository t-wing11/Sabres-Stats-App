const { fetchRoster } = require("./sampleData");

const resolvers = {
  Query: {
    teams: () => [
      { id: "BUF", name: "Buffalo Sabres", abbreviation: "BUF" },
    ],
    teamRoster: async (_, { teamId, season }) => {
      // Fetch roster for the given team and season
      return await fetchRoster(teamId, season);
    },
  },
};

module.exports = resolvers;
